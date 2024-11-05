package com.example.demoProjectoLabBack.model.dto;

import com.example.demoProjectoLabBack.persistance.entities.User;
import jakarta.validation.constraints.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class RatingDto {

    private String id;

    @Min(1) @Max(5)
    private int rating;

    @NotBlank @Size(min = 5, max = 128)
    private String comment;




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


}
