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


    public List<JobDto> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    private JobDto convertToDto(Job job) {
        return new JobDto(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getSkillsRequired()
        );
    }


    public Job createJob(JobDto jobDto) {
        Job job = new Job();
        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        job.setSkillsRequired(jobDto.getSkillsRequired());
        return jobRepository.save(job);
    }


    public void deleteJob(String jobId) {
        if (!jobRepository.existsById(jobId)) {
            throw new RuntimeException("Job not found with ID: " + jobId);
        }
        jobRepository.deleteById(jobId);
    }






}