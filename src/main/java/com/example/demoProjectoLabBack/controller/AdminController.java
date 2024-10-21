package com.example.demoProjectoLabBack.controller;

import com.example.demoProjectoLabBack.model.dto.JobDto;
import com.example.demoProjectoLabBack.model.dto.UserDto;
import com.example.demoProjectoLabBack.model.dto.UserForEditDto;
import com.example.demoProjectoLabBack.model.dto.UserForRegistrationDto;
import com.example.demoProjectoLabBack.persistance.entities.Job;
import com.example.demoProjectoLabBack.persistance.entities.User;
import com.example.demoProjectoLabBack.service.JobService;
import com.example.demoProjectoLabBack.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin Controller", description = "Admin operations such as user, job, and role management")
@PreAuthorize("hasRole('ADMIN')")  // Ensure only admins can access these endpoints
public class AdminController {


    @Autowired
    private JobService jobService;  // Assuming JobService is where the business logic resides

    // Endpoint to create a new job
    @PostMapping("/jobs")
    public ResponseEntity<Job> createJob(@RequestBody JobDto jobDto) {
        Job createdJob = jobService.createJob(jobDto);  // Converts JobDto to Job and saves it
        return ResponseEntity.status(HttpStatus.CREATED).body(createdJob);
    }


    @DeleteMapping("/jobs/{jobId}")
    public ResponseEntity<String> deleteJob(@PathVariable String jobId) {
        jobService.deleteJob(jobId);
        return ResponseEntity.ok("Job deleted successfully");
    }



}

