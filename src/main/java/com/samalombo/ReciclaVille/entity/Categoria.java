package com.samalombo.ReciclaVille.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "categorias")
@Data
@EqualsAndHashCode(exclude = {"categoriaPai", "subcategorias", "materiais"})
@ToString(exclude = {"categoriaPai", "subcategorias", "materiais"})
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name = "categoria_pai_id")
    private Categoria categoriaPai;
    
    @OneToMany(mappedBy = "categoriaPai", cascade = CascadeType.ALL)
    private List<Categoria> subcategorias;
    
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Material> materiais;
}