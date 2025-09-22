package com.samalombo.ReciclaVille.service;

import com.samalombo.ReciclaVille.dto.DashboardDTO;
import com.samalombo.ReciclaVille.repository.DeclaracaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {
    
    private final DeclaracaoRepository declaracaoRepository;
    
    public List<DashboardDTO> getDashboardData() {
        return declaracaoRepository.findDashboardData();
    }
    
    public List<DashboardDTO> getDashboardDataByCliente(Long clienteId) {
        return declaracaoRepository.findDashboardDataByCliente(clienteId);
    }
}