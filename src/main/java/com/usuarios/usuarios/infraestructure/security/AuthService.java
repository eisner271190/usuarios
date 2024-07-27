package com.usuarios.usuarios.infraestructure.security;

import java.util.HashMap;
import java.util.Map;

import com.usuarios.usuarios.infraestructure.jpa.entity.UserEntity;
import com.usuarios.usuarios.infraestructure.jpa.repository.IUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import lombok.RequiredArgsConstructor;

@Service
//@RequiredArgsConstructor
public class AuthService {

    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(IUserRepository userRepository,
                       JwtService jwtService,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserEntity user = userRepository.findByCorreo(request.getUsername()).orElseThrow();

        Map<String, Object> claims = new HashMap<>();
        claims.put("role",user.getId_rol().getNombre());
        claims.put("id_propietario",user.getId());

        String token = jwtService.getToken(user.getCorreo(), claims);
        return new AuthResponse(token);
    }

//    public AuthResponse register(RegisterRequest request) {
//        UserEntity user = new UserEntity();
//        user.setCorreo(request.getUsername());
//        user.setClave(passwordEncoder.encode(request.getPassword()));
//        user.setNombre(request.getFirstname());
//        user.setApellido(request.lastname);
//
//        userRepository.save(user);
//
//        return AuthResponse.builder()
//            .token(jwtService.getToken(user, new HashMap<>()))
//            .build();
//    }
}
