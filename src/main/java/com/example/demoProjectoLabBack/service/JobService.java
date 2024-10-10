package com.example.demoProjectoLabBack.service;



import com.example.demoProjectoLabBack.model.dto.JobDto;
import com.example.demoProjectoLabBack.persistance.entities.Job;
import com.example.demoProjectoLabBack.persistance.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    // Method to get all jobs
    public List<JobDto> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream()
                .map(this::convertToDto) // Convert each Job to JobDto
                .collect(Collectors.toList());
    }

    // Helper method to convert Job to JobDto
    private JobDto convertToDto(Job job) {
        return new JobDto(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getSkillsRequired()
        );
    }
}