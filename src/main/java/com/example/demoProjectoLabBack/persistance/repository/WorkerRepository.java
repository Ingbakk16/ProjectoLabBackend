package com.example.demoProjectoLabBack.persistance.repository;

import com.example.demoProjectoLabBack.persistance.entities.Job;
import com.example.demoProjectoLabBack.persistance.entities.WorkerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkerRepository extends JpaRepository<WorkerProfile, Integer> {



}