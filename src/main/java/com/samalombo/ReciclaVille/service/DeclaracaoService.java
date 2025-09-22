package com.samalombo.ReciclaVille.service;

import com.samalombo.ReciclaVille.dto.DeclaracaoDTO;
import com.samalombo.ReciclaVille.dto.ItemDeclaracaoDTO;
import com.samalombo.ReciclaVille.entity.*;
import com.samalombo.ReciclaVille.repository.ClienteRepository;
import com.samalombo.ReciclaVille.repository.DeclaracaoRepository;
import com.samalombo.ReciclaVille.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeclaracaoService {
    
    private final DeclaracaoRepository declaracaoRepository;
    private final ClienteRepository clienteRepository;
    private final MaterialRepository materialRepository;
    
    @Transactional
    public Declaracao criar(DeclaracaoDTO dto) {
        if (dto.getDataFinalPeriodo().isBefore(dto.getDataInicialPeriodo())) {
            throw new RuntimeException("Data final deve ser maior que data inicial");
        }
        
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        Declaracao declaracao = new Declaracao();
        declaracao.setCliente(cliente);
        declaracao.setDataDeclaracao(LocalDate.now());
        declaracao.setDataInicialPeriodo(dto.getDataInicialPeriodo());
        declaracao.setDataFinalPeriodo(dto.getDataFinalPeriodo());
        
        List<ItemDeclaracao> itens = new ArrayList<>();
        BigDecimal totalMateriais = BigDecimal.ZERO;
        BigDecimal totalCompensacao = BigDecimal.ZERO;
        
        for (ItemDeclaracaoDTO itemDto : dto.getItens()) {
            Material material = materialRepository.findById(itemDto.getMaterialId())
                    .orElseThrow(() -> new RuntimeException("Material não encontrado"));
            
            ItemDeclaracao item = new ItemDeclaracao();
            item.setDeclaracao(declaracao);
            item.setMaterial(material);
            item.setPercentualCompensacao(material.getPercentualCompensacao());
            item.setToneladasDeclaradas(itemDto.getToneladasDeclaradas());
            
            BigDecimal toneladasCompensacao = itemDto.getToneladasDeclaradas()
                    .multiply(material.getPercentualCompensacao())
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            item.setToneladasCompensacao(toneladasCompensacao);
            
            itens.add(item);
            totalMateriais = totalMateriais.add(itemDto.getToneladasDeclaradas());
            totalCompensacao = totalCompensacao.add(toneladasCompensacao);
        }
        
        declaracao.setItens(itens);
        declaracao.setTotalMateriais(totalMateriais);
        declaracao.setTotalCompensacao(totalCompensacao);
        
        return declaracaoRepository.save(declaracao);
    }
    
    public List<Declaracao> listarTodas() {
        return declaracaoRepository.findAll();
    }
    
    public List<Declaracao> listarPorCliente(Long clienteId) {
        return declaracaoRepository.findByClienteId(clienteId);
    }
    
    public Optional<Declaracao> buscarPorId(Long id) {
        return declaracaoRepository.findById(id);
    }
    
    public void deletar(Long id) {
        declaracaoRepository.deleteById(id);
    }
}