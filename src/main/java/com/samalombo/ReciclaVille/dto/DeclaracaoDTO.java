package com.samalombo.ReciclaVille.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class DeclaracaoDTO {
    private Long id;
    
    @NotNull(message = "Cliente é obrigatório")
    private Long clienteId;
    
    private LocalDate dataDeclaracao;
    
    @NotNull(message = "Data inicial do período é obrigatória")
    private LocalDate dataInicialPeriodo;
    
    @NotNull(message = "Data final do período é obrigatória")
    private LocalDate dataFinalPeriodo;
    
    private BigDecimal totalMateriais;
    
    private BigDecimal totalCompensacao;
    
    @NotEmpty(message = "Itens da declaração são obrigatórios")
    @Valid
    private List<ItemDeclaracaoDTO> itens;
}