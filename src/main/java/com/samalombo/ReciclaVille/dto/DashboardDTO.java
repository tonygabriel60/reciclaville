package com.samalombo.ReciclaVille.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DashboardDTO {
    private String material;
    private BigDecimal totalCompensacao;
}