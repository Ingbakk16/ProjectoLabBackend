package com.example.demoProjectoLabBack.model.dto;

public class WorkerProfileForEditDto {


    public String getImageUrl;
    private String description;
    private int dni;
    private String direccion;
    private String imageUrl;



    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getDni() { return dni; }
    public void setDni(int dni) { this.dni = dni; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }


}
