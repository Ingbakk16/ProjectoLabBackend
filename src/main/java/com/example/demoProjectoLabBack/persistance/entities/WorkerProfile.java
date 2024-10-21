package com.example.demoProjectoLabBack.persistance.entities;

import jakarta.persistence.*;
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
    private int dni;
    private String direccion;
    private double rating = 0.0;
    private String imageUrl;

    @DBRef
    private User user;

    @DBRef
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
        this.jobs.add(job);
        job.getWorkerProfiles().add(this);  // Ensure bidirectional relationship
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
