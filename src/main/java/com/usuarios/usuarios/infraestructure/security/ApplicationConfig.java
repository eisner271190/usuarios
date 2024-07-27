package com.usuarios.usuarios.infraestructure.security;

import com.usuarios.usuarios.infraestructure.exception.UserNotFountException;
import com.usuarios.usuarios.infraestructure.jpa.mapper.IUserEntityMapper;
import com.usuarios.usuarios.infraestructure.jpa.repository.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;

@Configuration
//@RequiredArgsConstructor
public class ApplicationConfig {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    public ApplicationConfig(IUserRepository userRepository,
                             IUserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public UserDetailsService userDetailService() {
        return username -> userEntityMapper.toUserDetails(userRepository.findByCorreo(username).orElseThrow(UserNotFountException::new));
    }
}
