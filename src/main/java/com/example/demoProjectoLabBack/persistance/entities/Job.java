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


    private Set<String> workerProfileIds = new HashSet<>();


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

    public Set<String> getWorkerProfileIds() {
        return workerProfileIds;
    }

    public void setWorkerProfileIds(Set<String> workerProfileIds) {
        this.workerProfileIds = workerProfileIds;
    }

    // Update to add only WorkerProfile ID
    public void addWorkerProfile(WorkerProfile workerProfile) {
        if (workerProfile.getId() == null) {
            throw new RuntimeException("Cannot add a worker profile with a null ID");
        }
        // Add only the worker profile ID to the Job's workerProfileIds set
        this.workerProfileIds.add(workerProfile.getId());
    }

}
