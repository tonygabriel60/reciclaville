package com.samalombo.ReciclaVille.service;

import com.samalombo.ReciclaVille.dto.ClienteDTO;
import com.samalombo.ReciclaVille.entity.Cliente;
import com.samalombo.ReciclaVille.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    
    private final ClienteRepository clienteRepository;
    
    public Cliente criar(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCnpj(dto.getCnpj());
        cliente.setAtividadeEconomica(dto.getAtividadeEconomica());
        cliente.setResponsavel(dto.getResponsavel());
        
        return clienteRepository.save(cliente);
    }
    
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }
    
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }
    
    public Cliente atualizar(Long id, ClienteDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        
        cliente.setNome(dto.getNome());
        cliente.setCnpj(dto.getCnpj());
        cliente.setAtividadeEconomica(dto.getAtividadeEconomica());
        cliente.setResponsavel(dto.getResponsavel());
        
        return clienteRepository.save(cliente);
    }
    
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}