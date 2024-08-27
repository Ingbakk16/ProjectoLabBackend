package com.example.demoProjectoLabBack.model.dto;

public class WorkerProfileDto {
    private Integer id;
    private String description;
    private int dni;
    private String direccion;
    private int rating;
    private UserDto user;  // Include a nested DTO for the user

    // Constructor
    public WorkerProfileDto(Integer id, String description, int dni, String direccion, int rating, UserDto user) {
        this.id = id;
        this.description = description;
        this.dni = dni;
        this.direccion = direccion;
        this.rating = rating;
        this.user = user;
    }

    // Getters and setters

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
