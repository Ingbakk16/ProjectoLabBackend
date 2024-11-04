package com.example.demoProjectoLabBack.model.dto;

public class UserDto {

    private String id;
    private String username;
    private String name;
    private String lastname;
    private String email;

    // Constructor
    public UserDto(String id, String username, String name, String lastname, String email) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    // Getters and setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
