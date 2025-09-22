package com.samalombo.ReciclaVille.controller;

import com.samalombo.ReciclaVille.dto.DashboardDTO;
import com.samalombo.ReciclaVille.service.DashboardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    
    private final DashboardService dashboardService;
    
    @GetMapping
    public ResponseEntity<List<DashboardDTO>> getDashboard(Authentication auth, HttpServletRequest request) {
        List<DashboardDTO> data;
        
        if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            Long clienteId = (Long) request.getAttribute("clienteId");
            if (clienteId == null) {
                return ResponseEntity.badRequest().build();
            }
            data = dashboardService.getDashboardDataByCliente(clienteId);
        } else {
            data = dashboardService.getDashboardData();
        }
        
        return ResponseEntity.ok(data);
    }
}