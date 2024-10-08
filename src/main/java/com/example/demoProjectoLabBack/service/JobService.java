package com.example.demoProjectoLabBack.service;



import com.example.demoProjectoLabBack.persistance.entities.Job;
import com.example.demoProjectoLabBack.persistance.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    // Create a new job
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    // Find a job by ID
    public Job findJobById(String id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }

    // Get all jobs
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // Delete a job by ID
    public void deleteJobById(String id) {
        jobRepository.deleteById(id);
    }

    // Update an existing job
    public Job updateJob(String id, Job updatedJob) {
        Optional<Job> existingJobOpt = jobRepository.findById(id);

        if (existingJobOpt.isPresent()) {
            Job existingJob = existingJobOpt.get();
            existingJob.setTitle(updatedJob.getTitle());
            existingJob.setDescription(updatedJob.getDescription());
            existingJob.setSkillsRequired(updatedJob.getSkillsRequired());
            return jobRepository.save(existingJob);
        } else {
            throw new RuntimeException("Job not found");
        }
    }
}
