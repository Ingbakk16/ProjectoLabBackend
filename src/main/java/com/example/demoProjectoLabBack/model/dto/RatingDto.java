package com.example.demoProjectoLabBack.model.dto;

import com.example.demoProjectoLabBack.persistance.entities.User;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class RatingDto {

    private int rating;  // The numeric score (e.g., 1 to 5)
    private String comment;  // The user's comment
    //private String ratedByUserId;  // The user ID of the rater

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

    //public String getRatedByUserId() {
       // return ratedByUserId;
   // }

   // public void setRatedByUserId(String ratedByUserId) {
       // this.ratedByUserId = ratedByUserId;
    //}
}
