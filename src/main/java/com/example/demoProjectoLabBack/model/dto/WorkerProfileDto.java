package com.example.demoProjectoLabBack.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class WorkerProfileDto {

    private String id;

    private String description;

    private int dni;

    private String direccion;

    private long phoneNumber;

    private double rating;

    private List<String> imageUrls;

    private UserDto user;

    private List<String> jobTitles;



    public WorkerProfileDto(String id, String description, int dni, String direccion, double rating, List<String> imageUrls, List<String> urls, UserDto user, List<String> jobTitles, long phoneNumber) {
        this.id = id;
        this.description = description;
        this.dni = dni;
        this.direccion = direccion;
        this.rating = rating;
        this.phoneNumber = phoneNumber;
        this.imageUrls = imageUrls;
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
    public List<String> getImageUrls() { return imageUrls; }
    public void setImageUrls(List<String> imageUrl) { this.imageUrls = imageUrl; }
    public long getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
