package com.example.demoProjectoLabBack.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

public class WorkerProfileDto {

    private String id;

    private String description;

    @NotBlank @Max(8)
    private int dni;

    @Size(min = 4, max = 32)
    private String direccion;

    private double rating;

    private String imageUrl;

    private UserDto user;

    private List<String> jobTitles;



    public WorkerProfileDto(String id, String description, int dni, String direccion, double rating, String imageUrl, UserDto user, List<String> jobTitles) {
        this.id = id;
        this.description = description;
        this.dni = dni;
        this.direccion = direccion;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.user = user;
        this.jobTitles = jobTitles;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getDni() { return dni; }
    public void setDni(int dni) { this.dni = dni; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public double getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public UserDto getUser() { return user; }
    public void setUser(UserDto user) { this.user = user; }
    public List<String> getJobTitles() { return jobTitles; }
    public void setJobTitles(List<String> jobTitles) { this.jobTitles = jobTitles; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
