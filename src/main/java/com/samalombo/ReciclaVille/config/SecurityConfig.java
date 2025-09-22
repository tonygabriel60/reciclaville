package com.samalombo.ReciclaVille.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/login", "/api/auth/test").permitAll()
                .requestMatchers("/h2-console/**").permitAll()
                
                .requestMatchers("/api/usuarios/**").hasRole("ADMIN")
                
                .requestMatchers(HttpMethod.GET, "/api/categorias/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/api/categorias/**").hasRole("ADMIN")
                
                .requestMatchers(HttpMethod.GET, "/api/materiais/**").hasAnyRole("ADMIN", "USER")
                .requestMatchers("/api/materiais/**").hasRole("ADMIN")
                
                .requestMatchers("/api/clientes/**").hasRole("ADMIN")
                
                .requestMatchers("/api/declaracoes/**").hasAnyRole("ADMIN", "USER")
                
                .requestMatchers("/api/dashboard/**").hasAnyRole("ADMIN", "USER")
                
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
        
        return http.build();
    }
}