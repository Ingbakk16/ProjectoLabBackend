package com.example.demoProjectoLabBack.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserForEditDto {

    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    private String username;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Last name is mandatory")
    private String lastname;


    public void setUsername(String username) {
        this.username = username;
    }
    public void setLastname(String lastname) {this.lastname = lastname;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getUsername() {
        return username;
    }
    public String getLastname() {
        return lastname;
    }

    public String getName() {
        return name;
    }
}



