package com.samalombo.ReciclaVille.repository;

import com.samalombo.ReciclaVille.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByCategoriaPaiId(Long categoriaPaiId);
    List<Categoria> findByCategoriaPaiIsNull();
}