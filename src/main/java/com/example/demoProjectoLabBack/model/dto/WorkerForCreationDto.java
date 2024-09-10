package com.example.demoProjectoLabBack.model.dto;

public class WorkerForCreationDto {
    private String description;
    private int dni;
    private String direccion;
    private int rating;
    private String jobId;


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

}