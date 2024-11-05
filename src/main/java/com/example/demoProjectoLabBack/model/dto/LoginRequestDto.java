package com.example.demoProjectoLabBack.model.dto;

import jakarta.validation.constraints.Size;

public class LoginRequestDto {

    @Size(min = 3, max = 16, message = "Username must be between 3 and 16 characters")
    private String username;

    @Size(min = 8,message = "Password must be at least 8 characters")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
