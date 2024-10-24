package com.example.demoProjectoLabBack.persistance.entities;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.UUID;

public class Rating {

    @Id
    private String id;

    private int rating;
    private String comment;

    @DBRef
    private User ratedBy;


    public Rating() {
        this.id = UUID.randomUUID().toString();  // Generate a unique ID when creating a new rating
    }

    public Rating(int score, String comment, User ratedBy) {
        this.rating = rating;
        this.comment = comment;
        this.ratedBy = ratedBy;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getters and Setters
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getRatedBy() {
        return ratedBy;
    }

    public void setRatedBy(User ratedBy) {
        this.ratedBy = ratedBy;
    }


}
