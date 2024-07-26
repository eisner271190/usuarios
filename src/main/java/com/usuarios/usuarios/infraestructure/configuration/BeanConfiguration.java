/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuarios.usuarios.infraestructure.configuration;

import com.usuarios.usuarios.domain.persistence.IRolPersistenceServicePort;
import com.usuarios.usuarios.domain.persistence.IUserPersistenceServicePort;
import com.usuarios.usuarios.domain.ports.IRolServicePort;
import com.usuarios.usuarios.domain.ports.IUserServicePort;
import com.usuarios.usuarios.domain.usecase.RolUseCase;
import com.usuarios.usuarios.domain.usecase.UserUseCase;
import com.usuarios.usuarios.infraestructure.jpa.adapter.RolAdapter;
import com.usuarios.usuarios.infraestructure.jpa.adapter.UserAdapter;
import com.usuarios.usuarios.infraestructure.jpa.mapper.RolEntityMapper;
import com.usuarios.usuarios.infraestructure.jpa.mapper.UserEntityMapper;
import com.usuarios.usuarios.infraestructure.jpa.repository.IRolRepository;
import com.usuarios.usuarios.infraestructure.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author usuario
 */
@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    
    private final IRolRepository rolRepository;
    private final RolEntityMapper rolEntityMapper;
    
    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    
    @Bean
    public IRolPersistenceServicePort rolPersistenceServicePort() {
        return new RolAdapter(rolRepository, rolEntityMapper);
    }
    
    @Bean
    public IRolServicePort rolServicePort() {
        return new RolUseCase(rolPersistenceServicePort());
    }
    
    @Bean
    public IUserPersistenceServicePort userPersistenceServicePort() {
        return new UserAdapter(userRepository, userEntityMapper);
    }
    
    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistenceServicePort());
    }
}
