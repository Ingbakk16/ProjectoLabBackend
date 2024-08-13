package com.example.demoProjectoLabBack.controller;



import com.example.demoProjectoLabBack.model.dto.UserForRegistrationDto;
import com.example.demoProjectoLabBack.persistance.entities.User;
import com.example.demoProjectoLabBack.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management System", description = "Operations pertaining to user in User Management System")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public User registerUser(@Validated @RequestBody UserForRegistrationDto userForRegistrationDto) {
        // Convert UserDTO to User
        User user = new User();
        user.setUsername(userForRegistrationDto.getUsername());
        user.setName(userForRegistrationDto.getName());
        user.setLastname(userForRegistrationDto.getLastname());
        user.setEmail(userForRegistrationDto.getEmail());
        user.setPassword(userForRegistrationDto.getPassword()); // Remember to encode the password

        return userService.createUser(user);
    }






    @GetMapping("/{id}")
    @Operation(summary = "Get a user by ID")
    public User getUserById(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    @GetMapping("/username/{username}")
    @Operation(summary = "Get a user by username")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user by ID")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        // For now, just return a simple message
        return "Welcome to the Dashboard!";
    }
}