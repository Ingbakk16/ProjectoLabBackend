package com.example.demoProjectoLabBack.controller;



import ch.qos.logback.classic.encoder.JsonEncoder;
import com.example.demoProjectoLabBack.config.SecurityConfig;
import com.example.demoProjectoLabBack.model.dto.UserForEditDto;
import com.example.demoProjectoLabBack.model.dto.UserForRegistrationDto;
import com.example.demoProjectoLabBack.model.enums.RoleName;
import com.example.demoProjectoLabBack.persistance.entities.Role;
import com.example.demoProjectoLabBack.persistance.entities.User;
import com.example.demoProjectoLabBack.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import  com.example.demoProjectoLabBack.persistance.repository.RoleRepository;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management System", description = "Operations pertaining to user in User Management System")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public User registerUser(@Validated @RequestBody UserForRegistrationDto userForRegistrationDto) {
        // Check if the username is already taken
        if (userService.isUsernameTaken(userForRegistrationDto.getUsername())) {
            throw new RuntimeException("Error: Username is already taken!");
        }

        // Convert UserDTO to User
        User user = new User();
        user.setUsername(userForRegistrationDto.getUsername());
        user.setName(userForRegistrationDto.getName());
        user.setLastname(userForRegistrationDto.getLastname());
        user.setEmail(userForRegistrationDto.getEmail());

        // Encode the password before saving
        String encodedPassword = passwordEncoder.encode(userForRegistrationDto.getPassword());
        user.setPassword(encodedPassword);

        // Fetch the default role ROLE_USER from MongoDB
        Role defaultRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        // Assign default role to the user
        user.setRole(defaultRole);

        // Save the user in MongoDB
        return userService.createUser(user);
    }






    @GetMapping("/{id}")
    @Operation(summary = "Get a user by ID")
    public User getUserById(@PathVariable String id) {
        return userService.findUserById(id);
    }




    @GetMapping("/all")
    @Operation(summary = "Get all users")
    public List<User> getAllUsers() {
        return userService.findAllUsersWithUserRole();
    }

    @GetMapping("/all_workers")
    @Operation(summary = "Get all workers")
    public List<User> getAllWorkers() {
        return userService.findAllUsersWithWorkerRole();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user by ID")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<User> editUser(
            @PathVariable String id,
            @Validated @RequestBody UserForEditDto userForEditDto) {

        // Find the existing user by ID
        User existingUser = userService.findUserById(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        // Update user fields using the userForEditDto object
        existingUser.setUsername(userForEditDto.getUsername());
        existingUser.setName(userForEditDto.getName());
        existingUser.setLastname(userForEditDto.getLastname());

        // Save the updated user
        User updatedUser = userService.updateUser(existingUser);

        return ResponseEntity.ok(updatedUser);
    }


}