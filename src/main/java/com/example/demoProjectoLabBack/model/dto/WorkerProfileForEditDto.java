package com.example.demoProjectoLabBack.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class WorkerProfileForEditDto {



    @Size(min = 3, max = 164, message = "Descriptionmust be between 3 and 164 characters")
    private String description;

    @Size(min = 4, max = 32, message = "Direccion must be between 4 and 32 characters")
    private String direccion;

    @NotNull(message = "Phone number is mandatory")
    private long phoneNumber;



    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public long getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
