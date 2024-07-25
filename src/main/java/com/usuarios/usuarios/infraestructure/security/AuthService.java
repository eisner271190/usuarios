package com.usuarios.usuarios.infraestructure.security;

import com.usuarios.usuarios.domain.model.UserModel;
import com.usuarios.usuarios.domain.repositories.IUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserModel user = userRepository.findByCorreo(request.getUsername()).orElseThrow();
        UserDetails userDetails = (UserDetails)user;
        
        String token = jwtService.getToken(userDetails, user.getId_rol().getNombre());
        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public AuthResponse register(RegisterRequest request) {
        UserModel user = new UserModel();
        user.setCorreo(request.getUsername());
        user.setClave(passwordEncoder.encode(request.getPassword()));
        user.setNombre(request.getFirstname());
        user.setApellido(request.lastname);

        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user, "Propietario"))
            .build();
    }
}
