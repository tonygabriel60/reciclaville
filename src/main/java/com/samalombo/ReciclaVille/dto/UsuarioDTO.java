package com.samalombo.ReciclaVille.dto;

import com.samalombo.ReciclaVille.enums.PerfilUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    @NotBlank(message = "Nome de usuário é obrigatório")
    private String nomeUsuario;
    
    @NotBlank(message = "Senha é obrigatória")
    private String senha;
    
    @NotNull(message = "Perfil é obrigatório")
    private PerfilUsuario perfil;
    
    private Long clienteId;
}