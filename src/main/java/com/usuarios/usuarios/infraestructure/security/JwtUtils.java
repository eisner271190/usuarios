/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.usuarios.usuarios.infraestructure.security;

import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author usuario
 */
@Component
public class JwtUtils {
    private static final String SECRET_KEY="586E3272357538782F413F4428472B4B6250655368566B597033733676397924";
    private static final String USERGENERATOR="AUTH0JWT-BACKEND";
    public static final long TOKEN_EXPIRATION_TIME = 3_600_000;

    public String createToken(UserDetails user, Map<String,Object> extraClaims)
    {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        String token;
        token = JWT.create()
                .withIssuer(USERGENERATOR)
                .withIssuedAt(new Date())
                .withSubject(user.getUsername())
                .withClaim("authorities", "ROLE_Propietario")
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .sign(algorithm);

        return token;
    }
    public DecodedJWT validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(USERGENERATOR)
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Token invalid, not Authorized");
        }
    }

    public String extractUsername(DecodedJWT decodedJWT){
        return decodedJWT.getSubject();
    }

    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName) {
        return decodedJWT.getClaim(claimName);
    }
}