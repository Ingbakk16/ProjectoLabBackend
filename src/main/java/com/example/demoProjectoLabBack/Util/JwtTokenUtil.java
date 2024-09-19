package com.example.demoProjectoLabBack.Util;

import io.jsonwebtoken.Jwts;

public class JwtTokenUtil {

    private String secretKey = "yourSecretKey";

    public String extractUserId(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                .getBody()
                .getSubject(); // Assumes userId is in the subject
    }

}
