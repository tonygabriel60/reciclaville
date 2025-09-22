package com.samalombo.ReciclaVille.controller;

import com.samalombo.ReciclaVille.config.JwtUtil;
import com.samalombo.ReciclaVille.dto.DeclaracaoDTO;
import com.samalombo.ReciclaVille.entity.Declaracao;
import com.samalombo.ReciclaVille.service.DeclaracaoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/declaracoes")
@RequiredArgsConstructor
public class DeclaracaoController {
    
    private final DeclaracaoService declaracaoService;
    private final JwtUtil jwtUtil;
    
    @PostMapping
    public ResponseEntity<Declaracao> criar(@Valid @RequestBody DeclaracaoDTO dto, 
                                          Authentication auth, HttpServletRequest request) {
        try {
            if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
                Long clienteIdToken = (Long) request.getAttribute("clienteId");
                if (clienteIdToken == null || !clienteIdToken.equals(dto.getClienteId())) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
            }
            
            Declaracao declaracao = declaracaoService.criar(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(declaracao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Declaracao>> listarTodas(Authentication auth, HttpServletRequest request) {
        List<Declaracao> declaracoes;
        
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            Long clienteId = (Long) request.getAttribute("clienteId");
            if (clienteId == null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            declaracoes = declaracaoService.listarPorCliente(clienteId);
        } else {
            declaracoes = declaracaoService.listarTodas();
        }
        
        return ResponseEntity.ok(declaracoes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Declaracao> buscarPorId(@PathVariable Long id, 
                                                Authentication auth, HttpServletRequest request) {
        return declaracaoService.buscarPorId(id)
                .map(declaracao -> {
                    if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
                        Long clienteId = (Long) request.getAttribute("clienteId");
                        if (clienteId == null || !clienteId.equals(declaracao.getCliente().getId())) {
                            return ResponseEntity.status(HttpStatus.FORBIDDEN).<Declaracao>build();
                        }
                    }
                    return ResponseEntity.ok(declaracao);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id, 
                                      Authentication auth, HttpServletRequest request) {
        try {
            if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
                Declaracao declaracao = declaracaoService.buscarPorId(id).orElse(null);
                if (declaracao == null) {
                    return ResponseEntity.notFound().build();
                }
                
                Long clienteId = (Long) request.getAttribute("clienteId");
                if (clienteId == null || !clienteId.equals(declaracao.getCliente().getId())) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
            }
            
            declaracaoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}