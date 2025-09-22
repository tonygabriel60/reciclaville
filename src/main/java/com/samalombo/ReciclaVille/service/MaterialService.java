package com.samalombo.ReciclaVille.service;

import com.samalombo.ReciclaVille.dto.MaterialDTO;
import com.samalombo.ReciclaVille.entity.Categoria;
import com.samalombo.ReciclaVille.entity.Material;
import com.samalombo.ReciclaVille.repository.CategoriaRepository;
import com.samalombo.ReciclaVille.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MaterialService {
    
    private final MaterialRepository materialRepository;
    private final CategoriaRepository categoriaRepository;
    
    public Material criar(MaterialDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        
        Material material = new Material();
        material.setNome(dto.getNome());
        material.setCategoria(categoria);
        material.setPercentualCompensacao(dto.getPercentualCompensacao());
        
        return materialRepository.save(material);
    }
    
    public List<Material> listarTodos(Long categoriaId) {
        if (categoriaId != null) {
            return materialRepository.findByCategoriaId(categoriaId);
        }
        return materialRepository.findAll();
    }
    
    public Optional<Material> buscarPorId(Long id) {
        return materialRepository.findById(id);
    }
    
    public Material atualizar(Long id, MaterialDTO dto) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material não encontrado"));
        
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        
        material.setNome(dto.getNome());
        material.setCategoria(categoria);
        material.setPercentualCompensacao(dto.getPercentualCompensacao());
        
        return materialRepository.save(material);
    }
    
    public void deletar(Long id) {
        materialRepository.deleteById(id);
    }
}