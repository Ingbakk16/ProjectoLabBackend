package com.example.demoProjectoLabBack.persistance.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class WorkerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;


    @Column
    private String description;

    @Column
    private int dni;

    @Column
    private String direccion;

    @Column
    private int rating;



    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id") // Specify column name explicitly
    private User user;

    @OneToMany(mappedBy = "workerProfile", cascade = CascadeType.ALL)
    private Set<Job> jobs;


    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}