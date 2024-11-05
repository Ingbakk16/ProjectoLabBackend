package com.example.demoProjectoLabBack.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;

public class WorkerForCreationDto {

    @Size(min = 3, max = 164, message = "Description must be between 3 and 164 characters")
    private String description;

    @NotNull(message = "DNI is mandatory")
    @Min(value = 1, message = "DNI must be a positive number")
    private Integer dni;

    @Size(min = 4, max = 32, message = "Direccion must be between 4 and 32 characters")
    private String direccion;

    @NotNull(message = "Phone number is mandatory")
    private Long phoneNumber;

    private int rating = 0;

    @NotBlank(message = "Job ID is mandatory")
    private String jobId;










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

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
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