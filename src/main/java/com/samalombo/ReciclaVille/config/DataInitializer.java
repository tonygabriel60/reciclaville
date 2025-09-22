package com.samalombo.ReciclaVille.config;

import com.samalombo.ReciclaVille.entity.Usuario;
import com.samalombo.ReciclaVille.enums.PerfilUsuario;
import com.samalombo.ReciclaVille.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.findByNomeUsuario("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setNomeUsuario("admin");
            admin.setSenha(passwordEncoder.encode("admin"));
            admin.setPerfil(PerfilUsuario.ADMIN);
            
            usuarioRepository.save(admin);
            System.out.println("Usuário admin criado com sucesso!");
        }
    }
}