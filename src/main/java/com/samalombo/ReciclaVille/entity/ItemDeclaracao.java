package com.samalombo.ReciclaVille.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_declaracao")
@Data
@EqualsAndHashCode(exclude = {"declaracao", "material"})
@ToString(exclude = {"declaracao", "material"})
public class ItemDeclaracao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "declaracao_id", nullable = false)
    private Declaracao declaracao;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;
    
    @Column(name = "percentual_compensacao", nullable = false, precision = 5, scale = 2)
    private BigDecimal percentualCompensacao;
    
    @Column(name = "toneladas_declaradas", nullable = false, precision = 10, scale = 2)
    private BigDecimal toneladasDeclaradas;
    
    @Column(name = "toneladas_compensacao", nullable = false, precision = 10, scale = 2)
    private BigDecimal toneladasCompensacao;
}