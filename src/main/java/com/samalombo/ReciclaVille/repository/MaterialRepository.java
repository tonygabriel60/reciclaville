package com.samalombo.ReciclaVille.repository;

import com.samalombo.ReciclaVille.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByCategoriaId(Long categoriaId);
}