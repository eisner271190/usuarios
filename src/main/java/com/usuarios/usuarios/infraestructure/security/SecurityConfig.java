package com.usuarios.usuarios.infraestructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
                          AuthenticationProvider authProvider) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authProvider = authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authRequest ->
              authRequest
//                .requestMatchers("/auth/**").permitAll()
//                .requestMatchers("/api/v1/users/auth/**").permitAll()
//                .requestMatchers("/api/v1/users/client/saveAccountClient").permitAll()
//                .requestMatchers("/api/v1/users/admin/**").hasRole("Administrador")
//                .requestMatchers("/api/v1/restaurantes/admin/**").hasRole("Administrador")
//                .requestMatchers("/api/v1/users/owner/**").hasRole("Propietario")
//                .requestMatchers("/api/v1/platos/owner/**").hasRole("Propietario")
//                .requestMatchers("/api/v1/restaurantes/owner/**").hasRole("Propietario")
//                .anyRequest().authenticated()
                .anyRequest().permitAll()
            )
            .sessionManagement(sessionManager -> sessionManager .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
