package com.samalombo.ReciclaVille.entity;

import com.samalombo.ReciclaVille.enums.PerfilUsuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "usuarios")
@Data
@EqualsAndHashCode(exclude = {"cliente"})
@ToString(exclude = {"cliente"})
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(name = "nome_usuario", nullable = false, unique = true)
    private String nomeUsuario;
    
    @Column(nullable = false)
    private String senha;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PerfilUsuario perfil;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}