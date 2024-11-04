package com.example.demoProjectoLabBack.persistance.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "workerProfiles")
public class WorkerProfile {

    @Id
    private String id;

    private String description;

    @Max(value = 99999999, message = "DNI must be up to 8 digits")
    @Indexed(unique = true)
    private Integer dni;


    @Size(min = 4, max = 32, message = "Direccion must be between 4 and 32 characters")
    private String direccion;

    @Indexed(unique = true)
    private Long phoneNumber;

    private double rating = 0.0;

    private List<String> imageUrls = new ArrayList<>();

    @DBRef
    private User user;

    private Set<String> jobIds = new HashSet<>();

    private List<Integer> ratings = new ArrayList<>();

    private List<Rating> comments = new ArrayList<>();


    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<String> getJobIds() {
        return jobIds;
    }

    public void setJobIds(Set<String> jobIds) {
        this.jobIds = jobIds;
    }


    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public void addImageUrl(String imageUrl) {
        if (imageUrls.size() < 3) {
            this.imageUrls.add(imageUrl);
        } else {
            throw new RuntimeException("Cannot add more than 3 images");
        }
    }

    public void removeImageUrl(String imageUrl) {
        this.imageUrls.remove(imageUrl);
    }


    public void addJobId(String jobId) {
        this.jobIds.add(jobId);
    }

    public List<Rating> getComments() {
        return comments;
    }

    public void setComments(List<Rating> comments) {
        this.comments = comments;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(List<Integer> comments) {
    }

    // Add rating method to append new ratings and calculate the average
    public void addRating(Rating rating) {
        this.comments.add(rating);
        this.ratings.add(rating.getRating());
        updateAverageRating();
    }

    public void updateAverageRating() {
        this.rating = this.ratings.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
    }

}
