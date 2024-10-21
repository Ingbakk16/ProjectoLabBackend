package com.example.demoProjectoLabBack.persistance.entities;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class Rating {

    private int rating;
    private String comment;

    @DBRef
    private User ratedBy;


    public Rating() {}

    public Rating(int score, String comment, User ratedBy) {
        this.rating = rating;
        this.comment = comment;
        this.ratedBy = ratedBy;
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
