package com.example.demoProjectoLabBack.controller;


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
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerProfileService;

    @Autowired
    private UserService userService;

    @PutMapping("worker/{userId}")
    public ResponseEntity<String> updateUserToWorker(
            @PathVariable Integer userId,
            @RequestBody WorkerForCreationDto request) {
        try {
            // Create the worker profile
            workerProfileService.updateUserToWorker(userId, request);
            // Update the user role
            userService.updateUserRole(userId, RoleName.ROLE_WORKER);
            return ResponseEntity.ok("User updated to worker successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user");
        }
    }



    @GetMapping("/by-job/{title}")
    public ResponseEntity<List<WorkerProfile>> getWorkersByJobTitle(@PathVariable String title) {
        List<WorkerProfile> workers = workerProfileService.getWorkersByJobTitle(title);
        return ResponseEntity.ok(workers);
    }


    @GetMapping("all")
    public ResponseEntity<List<WorkerProfileDto>> getAllWorkers() {
        List<WorkerProfileDto> workers = workerProfileService.findAllWorkers();
        return ResponseEntity.ok(workers);
    }



}