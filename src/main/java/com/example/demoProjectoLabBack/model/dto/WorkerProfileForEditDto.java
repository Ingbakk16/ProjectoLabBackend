package com.example.demoProjectoLabBack.model.dto;

public class WorkerProfileForEditDto {



    private String description;
    private String direccion;
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
