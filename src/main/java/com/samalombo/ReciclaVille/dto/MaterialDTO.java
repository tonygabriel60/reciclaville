package com.samalombo.ReciclaVille.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MaterialDTO {
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    @NotNull(message = "Categoria é obrigatória")
    private Long categoriaId;
    
    @NotNull(message = "Percentual de compensação é obrigatório")
    @DecimalMin(value = "0.00", message = "Percentual deve ser maior ou igual a 0")
    @DecimalMax(value = "100.00", message = "Percentual deve ser menor ou igual a 100")
    private BigDecimal percentualCompensacao;
}