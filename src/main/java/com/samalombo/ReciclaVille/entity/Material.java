package com.samalombo.ReciclaVille.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "materiais")
@Data
@EqualsAndHashCode(exclude = {"categoria", "itensDeclaracao"})
@ToString(exclude = {"categoria", "itensDeclaracao"})
public class Material {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
    
    @Column(name = "percentual_compensacao", nullable = false, precision = 5, scale = 2)
    private BigDecimal percentualCompensacao;
    
    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
    private List<ItemDeclaracao> itensDeclaracao;
}