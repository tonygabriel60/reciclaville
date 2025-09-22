package com.samalombo.ReciclaVille.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "clientes")
@Data
@EqualsAndHashCode(exclude = {"declaracoes", "usuarios"})
@ToString(exclude = {"declaracoes", "usuarios"})
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false, unique = true)
    private String cnpj;
    
    @Column(name = "atividade_economica", nullable = false)
    private String atividadeEconomica;
    
    @Column(nullable = false)
    private String responsavel;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Declaracao> declaracoes;
    
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Usuario> usuarios;
}