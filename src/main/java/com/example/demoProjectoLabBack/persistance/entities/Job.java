package com.example.demoProjectoLabBack.persistance.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "jobs")
public class Job {

    @Id
    private String id;

    @NotBlank @Size(min = 3, max = 24) @Indexed(unique = true)
    private String title;

    @Size(min = 10, max = 64)
    private String description;

    private String skillsRequired;

    @DBRef
    private Set<WorkerProfile> workerProfiles = new HashSet<>();

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Set<WorkerProfile> getWorkerProfiles() {
        return workerProfiles;
    }

    public void setWorkerProfiles(Set<WorkerProfile> workerProfiles) {
        this.workerProfiles = workerProfiles;
    }

    public void addWorkerProfile(WorkerProfile workerProfile) {
        this.workerProfiles.add(workerProfile);
        workerProfile.getJobs().add(this); // Ensure the worker profile knows about this job
    }

}
