package com.example.demoProjectoLabBack.persistance.repository;

import com.example.demoProjectoLabBack.persistance.entities.Job;
import com.example.demoProjectoLabBack.persistance.entities.WorkerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends MongoRepository<WorkerProfile, String> {
    // Additional query methods (if needed) can be defined here
}