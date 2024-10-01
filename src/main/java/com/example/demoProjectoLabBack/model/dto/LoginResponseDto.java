package com.example.demoProjectoLabBack.model.dto;

public class LoginResponseDto {

    private String token;

    public LoginResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
