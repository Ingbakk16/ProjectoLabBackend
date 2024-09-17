package com.example.demoProjectoLabBack.controller;


import com.example.demoProjectoLabBack.Util.JwtUserDetails;
import com.example.demoProjectoLabBack.config.SecurityConfig;
import com.example.demoProjectoLabBack.filter.JwtAuthenticationFilter;
import com.example.demoProjectoLabBack.model.dto.WorkerForCreationDto;
import com.example.demoProjectoLabBack.model.dto.WorkerProfileDto;
import com.example.demoProjectoLabBack.model.enums.RoleName;
import com.example.demoProjectoLabBack.persistance.entities.User;
import com.example.demoProjectoLabBack.persistance.entities.WorkerProfile;
import com.example.demoProjectoLabBack.service.UserService;
import com.example.demoProjectoLabBack.service.WorkerService;
import io.swagger.v3.oas.annotations.Operation;
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
    public ResponseEntity<String> updateUserToWorker(
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user");
        }
    }



    @GetMapping("/by-job/{title}")
    public ResponseEntity<List<WorkerProfile>> getWorkersByJobTitle(@PathVariable String title) {
        List<WorkerProfile> workers = workerService.getWorkersByJobTitle(title);
        return ResponseEntity.ok(workers);
    }


    @GetMapping("all")
    @Operation(summary = "Get all workersProfiles")
    public ResponseEntity<List<WorkerProfileDto>> getAllWorkers() {
        List<WorkerProfileDto> workers = workerService.findAllWorkers();
        return ResponseEntity.ok(workers);
    }

    @GetMapping("/profile")
    public ResponseEntity<WorkerProfileDto> getWorkerProfile() {
        // Extract the authenticated user's details from the SecurityContextHolder
        JwtUserDetails userDetails = (JwtUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Extract the user ID from the token claims (or the principal)
        String userId = userDetails.getId(); // Assuming JwtUserDetails has a getId() method

        // Fetch the worker profile using the extracted userId
        WorkerProfileDto workerProfileDto = workerService.getWorkerProfileById(userId);

        return ResponseEntity.ok(workerProfileDto);
    }


}