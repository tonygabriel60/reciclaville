package com.samalombo.ReciclaVille.service;

import com.samalombo.ReciclaVille.dto.CategoriaDTO;
import com.samalombo.ReciclaVille.entity.Categoria;
import com.samalombo.ReciclaVille.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    
    private final CategoriaRepository categoriaRepository;
    
    public Categoria criar(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());
        
        if (dto.getCategoriaPaiId() != null) {
            Categoria categoriaPai = categoriaRepository.findById(dto.getCategoriaPaiId())
                    .orElseThrow(() -> new RuntimeException("Categoria pai não encontrada"));
            categoria.setCategoriaPai(categoriaPai);
        }
        
        return categoriaRepository.save(categoria);
    }
    
    public List<Categoria> listarTodas(Long categoriaPaiId) {
        if (categoriaPaiId != null) {
            return categoriaRepository.findByCategoriaPaiId(categoriaPaiId);
        }
        return categoriaRepository.findAll();
    }
    
    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }
    
    public Categoria atualizar(Long id, CategoriaDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        
        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());
        
        if (dto.getCategoriaPaiId() != null) {
            Categoria categoriaPai = categoriaRepository.findById(dto.getCategoriaPaiId())
                    .orElseThrow(() -> new RuntimeException("Categoria pai não encontrada"));
            categoria.setCategoriaPai(categoriaPai);
        } else {
            categoria.setCategoriaPai(null);
        }
        
        return categoriaRepository.save(categoria);
    }
    
    public void deletar(Long id) {
        categoriaRepository.deleteById(id);
    }
}