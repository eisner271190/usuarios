package com.usuarios.usuarios.infraestructure.security;

import com.usuarios.usuarios.domain.model.UserModel;
import com.usuarios.usuarios.domain.repositories.IUserRepository;
import java.util.HashMap;
import java.util.Map;
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
        
        Map<String,Object> extraClaims = new HashMap<>();
        extraClaims.put("ROLE_", user.getId_rol().getNombre());
        extraClaims.put("role", "ROLE_" + user.getId_rol().getNombre());
        extraClaims.put("email", user.getCorreo());
        extraClaims.put("nombre", user.getNombre());
        extraClaims.put("id", user.getId());
        extraClaims.put("identificacion", user.getNumero_documento());
        
        String token = jwtService.getToken(userDetails, extraClaims);
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
        
        Map<String,Object> extraClaims = new HashMap<>();

        return AuthResponse.builder()
            .token(jwtService.getToken(user, extraClaims))
            .build();
    }
}
