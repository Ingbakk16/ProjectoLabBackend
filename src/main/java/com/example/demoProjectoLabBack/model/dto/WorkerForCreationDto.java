package com.example.demoProjectoLabBack.model.dto;

import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;

public class WorkerForCreationDto {

    private String description;

    @NotBlank
    private int dni;


    @Size(min = 4, max = 32)
    private String direccion;


    private int rating = 0;

    @NotBlank
    private String jobId;

    private String imageUrl;


    // Getters and setters






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

    public void setDirection(String direccion) {
        this.direccion = direccion;
    }



    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getJobId() {
        return String.valueOf(jobId);
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setImageUrl (String imageUrl){ this.imageUrl = imageUrl;}
    public String getImageUrl() {return String.valueOf(imageUrl);}

}