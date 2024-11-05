package com.example.demoProjectoLabBack.controller;



import ch.qos.logback.classic.encoder.JsonEncoder;
import com.example.demoProjectoLabBack.Util.JwtUserDetails;
import com.example.demoProjectoLabBack.config.SecurityConfig;
import com.example.demoProjectoLabBack.model.dto.UserDto;
import com.example.demoProjectoLabBack.model.dto.UserForEditDto;
import com.example.demoProjectoLabBack.model.dto.UserForRegistrationDto;
import com.example.demoProjectoLabBack.model.dto.WorkerProfileDto;
import com.example.demoProjectoLabBack.model.enums.RoleName;
import com.example.demoProjectoLabBack.persistance.entities.Role;
import com.example.demoProjectoLabBack.persistance.entities.User;
import com.example.demoProjectoLabBack.persistance.entities.WorkerProfile;
import com.example.demoProjectoLabBack.service.UserService;
import com.example.demoProjectoLabBack.service.WorkerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import  com.example.demoProjectoLabBack.persistance.repository.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management System", description = "Operations pertaining to user in User Management System")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public User registerUser(@Validated @RequestBody UserForRegistrationDto userForRegistrationDto) {
        if (userService.isUsernameTaken(userForRegistrationDto.getUsername())) {
            throw new RuntimeException("Error: Username is already taken!");
        }
        if (userService.isEmailTaken(userForRegistrationDto.getEmail())){
            throw new RuntimeException("Error: Email is already taken!");
        }

        User user = new User();
        user.setUsername(userForRegistrationDto.getUsername());
        user.setName(userForRegistrationDto.getName());
        user.setLastname(userForRegistrationDto.getLastname());
        user.setEmail(userForRegistrationDto.getEmail());


        String encodedPassword = passwordEncoder.encode(userForRegistrationDto.getPassword());
        user.setPassword(encodedPassword);


        Role defaultRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));


        user.setRole(defaultRole);


        return userService.createUser(user);
    }






    @GetMapping("/profile")
    @Operation(summary = "Get the authenticated user's profile")
    public User getUserProfile(@AuthenticationPrincipal JwtUserDetails jwtUserDetails) {

        String userId = jwtUserDetails.getId();


        return userService.findUserById(userId);
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

    @DeleteMapping("/delete")
    @Operation(summary = "Delete a user by ID")
    public void deleteUser(@AuthenticationPrincipal JwtUserDetails jwtUserDetails) {

        String userId = jwtUserDetails.getId();

        userService.deleteUser(userId);
    }

    @PutMapping("/edit")
    @Operation(summary = "Update the authenticated user's profile")
    public ResponseEntity<UserDto> editUserProfile(
            @AuthenticationPrincipal JwtUserDetails jwtUserDetails,
            @RequestBody UserDto updateData) {


        String userId = jwtUserDetails.getId();


        UserDto updatedUser = userService.updateUserProfile(userId, updateData);

        return ResponseEntity.ok(updatedUser);
    }




}