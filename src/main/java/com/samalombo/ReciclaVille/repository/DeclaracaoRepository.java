package com.samalombo.ReciclaVille.repository;

import com.samalombo.ReciclaVille.dto.DashboardDTO;
import com.samalombo.ReciclaVille.entity.Declaracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeclaracaoRepository extends JpaRepository<Declaracao, Long> {
    List<Declaracao> findByClienteId(Long clienteId);
    
    @Query("SELECT new com.samalombo.ReciclaVille.dto.DashboardDTO(m.nome, SUM(i.toneladasCompensacao)) " +
           "FROM ItemDeclaracao i JOIN i.material m " +
           "GROUP BY m.nome")
    List<DashboardDTO> findDashboardData();
    
    @Query("SELECT new com.samalombo.ReciclaVille.dto.DashboardDTO(m.nome, SUM(i.toneladasCompensacao)) " +
           "FROM ItemDeclaracao i JOIN i.material m JOIN i.declaracao d " +
           "WHERE d.cliente.id = :clienteId " +
           "GROUP BY m.nome")
    List<DashboardDTO> findDashboardDataByCliente(@Param("clienteId") Long clienteId);
}