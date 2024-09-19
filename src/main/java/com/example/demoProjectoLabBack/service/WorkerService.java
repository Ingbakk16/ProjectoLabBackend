package com.example.demoProjectoLabBack.service;

import com.example.demoProjectoLabBack.model.dto.UserDto;
import com.example.demoProjectoLabBack.model.dto.WorkerForCreationDto;
import com.example.demoProjectoLabBack.model.dto.WorkerProfileDto;
import com.example.demoProjectoLabBack.persistance.entities.Job;
import com.example.demoProjectoLabBack.persistance.entities.User;
import com.example.demoProjectoLabBack.persistance.entities.WorkerProfile;
import com.example.demoProjectoLabBack.persistance.repository.JobRepository;
import com.example.demoProjectoLabBack.persistance.repository.UserRepository;
import com.example.demoProjectoLabBack.persistance.repository.WorkerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.stream.Collectors;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void updateUserToWorker(String userId, WorkerForCreationDto request) {
        // Fetch the user by ID from MongoDB
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create or update the WorkerProfile for the user
        WorkerProfile workerProfile = new WorkerProfile();
        workerProfile.setUser(user);
        workerProfile.setDescription(request.getDescription());
        workerProfile.setDni(request.getDni());
        workerProfile.setDireccion(request.getDireccion());
        workerProfile.setRating(request.getRating());



        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        workerProfile.addJob(job);  // Add the job to workerProfile

        // Save the updated WorkerProfile and Job association
        workerRepository.save(workerProfile);
    }


    public List<WorkerProfile> getWorkersByJobTitle(String title) {
        List<Job> jobs = jobRepository.findByTitle(title);
        if (jobs.isEmpty()) {
            return Collections.emptyList(); // Return an empty list if no jobs found
        }
        return jobs.stream()
                .map(Job::getWorkerProfiles)
                .flatMap(Set::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<WorkerProfileDto> findAllWorkers() {
        List<WorkerProfile> workers = workerRepository.findAll();
        if (workers == null || workers.isEmpty()) {
            // Log or handle the case where no workers are found
            return Collections.emptyList();
        }
        return workers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }



    public WorkerProfileDto getWorkerProfileById(String id) {
        WorkerProfile workerProfile = workerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Worker not found with ID: " + id));
        return convertToDto(workerProfile);
    }




    private WorkerProfileDto convertToDto(WorkerProfile workerProfile) {
        if (workerProfile == null) {
            return null; // or throw an exception
        }

        User user = workerProfile.getUser();
        UserDto userDto = (user != null) ? new UserDto(user.getId(), user.getUsername(), user.getName(), user.getLastname(), user.getEmail()) : null;

        List<String> jobTitles = workerProfile.getJobs() != null ?
                workerProfile.getJobs().stream()
                        .filter(Objects::nonNull) // Ensure Job is not null
                        .map(Job::getTitle)
                        .collect(Collectors.toList()) : Collections.emptyList();

        return new WorkerProfileDto(workerProfile.getId(),
                workerProfile.getDescription(),
                workerProfile.getDni(),
                workerProfile.getDireccion(),
                workerProfile.getRating(),
                userDto,
                jobTitles);
    }


    public void rateWorker(String workerId, int rating) {
        Optional<WorkerProfile> workerProfileOptional = workerRepository.findById(workerId);

        if (workerProfileOptional.isPresent()) {
            WorkerProfile workerProfile = workerProfileOptional.get();
            workerProfile.addRating(rating); // Add rating and recalculate average
            workerRepository.save(workerProfile); // Save the updated profile
        } else {
            throw new RuntimeException("Worker not found");
        }
    }


}
