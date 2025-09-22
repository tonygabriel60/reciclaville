package com.samalombo.ReciclaVille.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank(message = "Nome de usuário é obrigatório")
    private String nomeUsuario;

    @NotBlank(message = "Senha é obrigatória")
    private String senha;
}