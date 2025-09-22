package com.samalombo.ReciclaVille.controller;

import com.samalombo.ReciclaVille.config.JwtUtil;
import com.samalombo.ReciclaVille.dto.LoginDTO;
import com.samalombo.ReciclaVille.entity.Usuario;
import com.samalombo.ReciclaVille.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    
    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("API funcionando");
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            if (loginDTO.getNomeUsuario() == null || loginDTO.getSenha() == null) {
                return ResponseEntity.badRequest().body("Dados inválidos");
            }
            
            Usuario usuario = usuarioService.buscarPorNomeUsuario(loginDTO.getNomeUsuario())
                    .orElse(null);
            
            if (usuario == null) {
                return ResponseEntity.badRequest().body("Usuário não encontrado");
            }
            
            if (!passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenha())) {
                return ResponseEntity.badRequest().body("Senha inválida");
            }
            
            Long clienteId = usuario.getCliente() != null ? usuario.getCliente().getId() : null;
            String token = jwtUtil.generateToken(usuario.getNomeUsuario(), usuario.getPerfil().name(), clienteId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("perfil", usuario.getPerfil());
            response.put("nome", usuario.getNome());
            if (clienteId != null) {
                response.put("clienteId", clienteId);
            }
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }
}