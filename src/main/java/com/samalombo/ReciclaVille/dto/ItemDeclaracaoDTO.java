package com.samalombo.ReciclaVille.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemDeclaracaoDTO {
    private Long id;
    
    private Long declaracaoId;
    
    @NotNull(message = "Material é obrigatório")
    private Long materialId;
    
    private BigDecimal percentualCompensacao;
    
    @NotNull(message = "Toneladas declaradas é obrigatório")
    @DecimalMin(value = "0.01", message = "Toneladas declaradas deve ser maior que zero")
    private BigDecimal toneladasDeclaradas;
    
    private BigDecimal toneladasCompensacao;
}