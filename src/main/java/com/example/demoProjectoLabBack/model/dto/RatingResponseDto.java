package com.example.demoProjectoLabBack.model.dto;

public class RatingResponseDto {

    private String id;
    private int rating;
    private String comment;
    private String ratedByUserId;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getRatedByUserId() {
        return ratedByUserId;
    }

    public void setRatedByUserId(String ratedByUserId) {
        this.ratedByUserId = ratedByUserId;
    }

}
