package com.samalombo.ReciclaVille.service;

import com.samalombo.ReciclaVille.dto.UsuarioDTO;
import com.samalombo.ReciclaVille.entity.Cliente;
import com.samalombo.ReciclaVille.entity.Usuario;
import com.samalombo.ReciclaVille.enums.PerfilUsuario;
import com.samalombo.ReciclaVille.repository.ClienteRepository;
import com.samalombo.ReciclaVille.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    
    public Usuario criar(UsuarioDTO dto) {
        if (dto.getPerfil() == PerfilUsuario.USER && dto.getClienteId() == null) {
            throw new RuntimeException("Usuário com perfil USER deve ter um cliente associado");
        }
        
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setPerfil(dto.getPerfil());
        
        if (dto.getClienteId() != null) {
            Cliente cliente = clienteRepository.findById(dto.getClienteId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
            usuario.setCliente(cliente);
        }
        
        return usuarioRepository.save(usuario);
    }
    
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
    
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }
    
    public Optional<Usuario> buscarPorNomeUsuario(String nomeUsuario) {
        return usuarioRepository.findByNomeUsuario(nomeUsuario);
    }
    
    public Usuario atualizar(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        if (dto.getPerfil() == PerfilUsuario.USER && dto.getClienteId() == null) {
            throw new RuntimeException("Usuário com perfil USER deve ter um cliente associado");
        }
        
        usuario.setNome(dto.getNome());
        usuario.setNomeUsuario(dto.getNomeUsuario());
        if (!dto.getSenha().isEmpty()) {
            usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        }
        usuario.setPerfil(dto.getPerfil());
        
        if (dto.getClienteId() != null) {
            Cliente cliente = clienteRepository.findById(dto.getClienteId())
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
            usuario.setCliente(cliente);
        } else {
            usuario.setCliente(null);
        }
        
        return usuarioRepository.save(usuario);
    }
    
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}