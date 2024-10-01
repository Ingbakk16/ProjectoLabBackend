package com.example.demoProjectoLabBack.Util;

public class JwtResponse {

    private String token;

    // You can also include other fields if needed, such as expiration time or user details

    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
