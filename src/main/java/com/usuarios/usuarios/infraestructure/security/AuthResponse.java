package com.usuarios.usuarios.infraestructure.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@Builder
//@NoArgsConstructor
public class AuthResponse {
    String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    // Setter para token
    public void setToken(String token) {
        this.token = token;
    }
}
