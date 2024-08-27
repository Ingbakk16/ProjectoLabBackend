package com.example.demoProjectoLabBack.model.dto;

import com.example.demoProjectoLabBack.persistance.entities.User;

public class WorkerForCreationDto {
    private String description;
    private int dni;
    private String direccion;
    private int rating;
    private Integer jobId;


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

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

}