package com.samalombo.ReciclaVille.controller;

import com.samalombo.ReciclaVille.dto.MaterialDTO;
import com.samalombo.ReciclaVille.entity.Material;
import com.samalombo.ReciclaVille.service.MaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiais")
@RequiredArgsConstructor
public class MaterialController {
    
    private final MaterialService materialService;
    
    @PostMapping
    public ResponseEntity<Material> criar(@Valid @RequestBody MaterialDTO dto) {
        try {
            Material material = materialService.criar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(material);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Material>> listarTodos(@RequestParam(required = false) Long categoriaId) {
        List<Material> materiais = materialService.listarTodos(categoriaId);
        return ResponseEntity.ok(materiais);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Material> buscarPorId(@PathVariable Long id) {
        return materialService.buscarPorId(id)
                .map(material -> ResponseEntity.ok(material))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Material> atualizar(@PathVariable Long id, @Valid @RequestBody MaterialDTO dto) {
        try {
            Material material = materialService.atualizar(id, dto);
            return ResponseEntity.ok(material);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            materialService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}