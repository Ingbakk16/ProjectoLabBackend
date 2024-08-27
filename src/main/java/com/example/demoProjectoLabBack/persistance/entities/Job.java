package com.example.demoProjectoLabBack.persistance.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column
    private String skillsRequired;


    @ManyToMany(mappedBy = "jobs")
    private Set<WorkerProfile> workerProfiles = new HashSet<>();

    // Getter and setter for workerProfiles
    public Set<WorkerProfile> getWorkerProfiles() {
        return workerProfiles;
    }

    public void setWorkerProfiles(Set<WorkerProfile> workerProfiles) {
        this.workerProfiles = workerProfiles;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkillsRequired() {
        return skillsRequired;
    }

    public void setSkillsRequired(String skillsRequired) {
        this.skillsRequired = skillsRequired;
    }


    public WorkerProfile getWorkerProfile() {
        return (WorkerProfile) workerProfiles;
    }





}
