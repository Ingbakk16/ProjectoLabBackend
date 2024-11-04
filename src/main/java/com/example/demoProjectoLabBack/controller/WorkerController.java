package com.example.demoProjectoLabBack.controller;


import com.example.demoProjectoLabBack.Util.JwtUserDetails;
import com.example.demoProjectoLabBack.config.SecurityConfig;
import com.example.demoProjectoLabBack.filter.JwtAuthenticationFilter;
import com.example.demoProjectoLabBack.model.dto.*;
import com.example.demoProjectoLabBack.model.enums.RoleName;
import com.example.demoProjectoLabBack.persistance.entities.User;
import com.example.demoProjectoLabBack.persistance.entities.WorkerProfile;
import com.example.demoProjectoLabBack.service.UserService;
import com.example.demoProjectoLabBack.service.WorkerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityConfig securityConfig;

    @PutMapping("worker")
    @Operation(summary = "Create a WorkerUser")
    public ResponseEntity<String> updateUserToWorker(
            @Valid
            @RequestBody WorkerForCreationDto request,
            @AuthenticationPrincipal JwtUserDetails jwtUserDetails) {


        try {
            // Extract userId from the token
            String userId = jwtUserDetails.getId();

            // Create the worker profile
            workerService.updateUserToWorker(userId, request);

            // Update the user role to ROLE_WORKER
            userService.updateUserRole(userId, RoleName.ROLE_WORKER);

            return ResponseEntity.ok("User updated to worker successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user: " + e.getMessage());
        }
    }



    @GetMapping("/by-job/{jobId}")
    public List<WorkerProfileDto> getWorkerProfilesByJobId(@PathVariable String jobId) {
        return workerService.getWorkerProfilesByJobId(jobId);
    }


    @GetMapping("all")
    @Operation(summary = "Get all workersProfiles")
    public ResponseEntity<List<WorkerProfileDto>> getAllWorkers() {
        List<WorkerProfileDto> workers = workerService.findAllWorkers();
        return ResponseEntity.ok(workers);
    }

    @GetMapping("/profile")
    @Operation(summary = "Get the authenticated user's worker profile")
    public ResponseEntity<WorkerProfileDto> getWorkerProfile(@AuthenticationPrincipal JwtUserDetails jwtUserDetails) {
        // Extract the userId from the JwtUserDetails (which holds the token claims)
        String userId = jwtUserDetails.getId();

        // Fetch the worker profile using the extracted userId
        WorkerProfileDto workerProfileDto = workerService.getWorkerProfileByUserId(userId);

        return ResponseEntity.ok(workerProfileDto);
    }



    @PutMapping("/edit_profile")
    @Operation(summary = "Update the authenticated user's worker profile")
    public ResponseEntity<WorkerProfileDto> updateWorkerProfile(
            @AuthenticationPrincipal JwtUserDetails jwtUserDetails,
            @RequestBody WorkerProfileForEditDto updateData) {

        // Extract userId from token
        String userId = jwtUserDetails.getId();

        // Call the service to update the worker profile
        WorkerProfileDto updatedProfile = workerService.updateWorkerProfile(userId, updateData);

        return ResponseEntity.ok(updatedProfile);
    }



    @PostMapping("/{workerId}/rate")
    @Operation(summary = "Rate a Worker by an authenticated user")
    public ResponseEntity<String> rateWorker(
            @PathVariable String workerId,
            @RequestBody RatingDto ratingDto,
            @AuthenticationPrincipal JwtUserDetails jwtUserDetails) {


        try {
            int rating = ratingDto.getRating();
            String comment = ratingDto.getComment();
            String userId = jwtUserDetails.getId(); // user who rated

            // Call the service to handle rating logic
            workerService.rateWorker(workerId, ratingDto, userId);

            return ResponseEntity.ok("Rating added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding rating");
        }
    }

    @GetMapping("/{workerId}/comments")
    @Operation(summary = "Get all the comments of a worker by id")
    public ResponseEntity<List<RatingResponseDto>> getWorkerComments(@PathVariable String workerId) {
        try {
            List<RatingResponseDto> comments = workerService.getWorkerComments(workerId);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/images/add")
    public ResponseEntity<String> addImageToWorkerProfile(
            @RequestBody String imageUrl,
            @AuthenticationPrincipal JwtUserDetails jwtUserDetails) {
        try {
            String userId = jwtUserDetails.getId();


            workerService.addImageToWorkerProfile(userId, imageUrl);
            return ResponseEntity.ok("Image added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @DeleteMapping("/images/delete")
    public ResponseEntity<String> removeImageFromWorkerProfile(
            @RequestBody String imageUrl,
            @AuthenticationPrincipal JwtUserDetails jwtUserDetails
            ) {
        try {

            String userId = jwtUserDetails.getId();


            workerService.removeImageFromWorkerProfile(userId, imageUrl);
            return ResponseEntity.ok("Image removed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}


