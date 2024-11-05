package com.example.demoProjectoLabBack.model.dto;

public class JobDto {

    private String id;
    private String title;
    private String description;
    private String skillsRequired;


    public JobDto(String id, String title, String description, String skillsRequired) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.skillsRequired = skillsRequired;
    }


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
}
