package com.samalombo.ReciclaVille.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "declaracoes")
@Data
@EqualsAndHashCode(exclude = {"cliente", "itens"})
@ToString(exclude = {"cliente", "itens"})
public class Declaracao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    @Column(name = "data_declaracao", nullable = false)
    private LocalDate dataDeclaracao;
    
    @Column(name = "data_inicial_periodo", nullable = false)
    private LocalDate dataInicialPeriodo;
    
    @Column(name = "data_final_periodo", nullable = false)
    private LocalDate dataFinalPeriodo;
    
    @Column(name = "total_materiais", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalMateriais;
    
    @Column(name = "total_compensacao", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalCompensacao;
    
    @OneToMany(mappedBy = "declaracao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemDeclaracao> itens;
}