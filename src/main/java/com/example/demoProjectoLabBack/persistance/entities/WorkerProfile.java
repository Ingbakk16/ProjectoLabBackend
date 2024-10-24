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

    @Max(8) @Column(unique = true)
    private int dni;


    @Size(min = 4, max = 32)
    private String direccion;

    private double rating = 0.0;

    private String imageUrl;

    @DBRef
    private User user;

    private Set<Job> jobs = new HashSet<>();

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

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public void addJob(Job job) {
        this.jobs.add(job);  // Make sure this adds the job properly
    }

    public List<Rating> getComments() {
        return comments;
    }

    public void setComments(List<Rating> comments) {
        this.comments = comments;
    }

    // Add rating method to append new ratings and calculate the average
    public void addRating(Rating rating) {
        this.comments.add(rating);
        this.ratings.add(rating.getRating());
        updateAverageRating();
    }

    private void updateAverageRating() {
        this.rating = this.ratings.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
    }

}
