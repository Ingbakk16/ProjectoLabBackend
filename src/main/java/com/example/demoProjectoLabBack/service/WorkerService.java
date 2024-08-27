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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerProfileRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    public void updateUserToWorker(Integer userId, WorkerForCreationDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        WorkerProfile workerProfile = new WorkerProfile();
        workerProfile.setUser(user);
        workerProfile.setDescription(request.getDescription());
        workerProfile.setDni(request.getDni());
        workerProfile.setDirection(request.getDireccion());
        workerProfile.setRating(request.getRating());

        workerProfileRepository.save(workerProfile);

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        workerProfile.addJob(job);

        // Save the updated WorkerProfile and Job
        workerProfileRepository.save(workerProfile);
    }


    public List<WorkerProfile> getWorkersByJobTitle(String title) {
        List<Job> jobs = jobRepository.findByTitle(title);
        return jobs.stream()
                .map(Job::getWorkerProfile)
                .distinct() // Remove duplicates if a worker has multiple jobs with the same title
                .collect(Collectors.toList());
    }


    public List<WorkerProfileDto> findAllWorkers() {
        List<WorkerProfile> workers = workerProfileRepository.findAll();
        return workers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private WorkerProfileDto convertToDto(WorkerProfile workerProfile) {
        User user = workerProfile.getUser();
        UserDto userDto = new UserDto(user.getId(), user.getUsername(), user.getName(), user.getLastname(), user.getEmail());
        return new WorkerProfileDto(workerProfile.getId(), workerProfile.getDescription(), workerProfile.getDni(),
                workerProfile.getDireccion(), workerProfile.getRating(), userDto);
    }


}
