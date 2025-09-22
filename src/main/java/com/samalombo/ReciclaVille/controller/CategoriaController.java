package com.samalombo.ReciclaVille.controller;

import com.samalombo.ReciclaVille.dto.CategoriaDTO;
import com.samalombo.ReciclaVille.entity.Categoria;
import com.samalombo.ReciclaVille.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    
    private final CategoriaService categoriaService;
    
    @PostMapping
    public ResponseEntity<Categoria> criar(@Valid @RequestBody CategoriaDTO dto) {
        try {
            Categoria categoria = categoriaService.criar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodas(@RequestParam(required = false) Long categoriaPaiId) {
        List<Categoria> categorias = categoriaService.listarTodas(categoriaPaiId);
        return ResponseEntity.ok(categorias);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
        return categoriaService.buscarPorId(id)
                .map(categoria -> ResponseEntity.ok(categoria))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaDTO dto) {
        try {
            Categoria categoria = categoriaService.atualizar(id, dto);
            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            categoriaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}