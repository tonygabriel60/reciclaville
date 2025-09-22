package com.samalombo.ReciclaVille.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteDTO {
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    @NotBlank(message = "CNPJ é obrigatório")
    private String cnpj;
    
    @NotBlank(message = "Atividade econômica é obrigatória")
    private String atividadeEconomica;
    
    @NotBlank(message = "Responsável é obrigatório")
    private String responsavel;
}