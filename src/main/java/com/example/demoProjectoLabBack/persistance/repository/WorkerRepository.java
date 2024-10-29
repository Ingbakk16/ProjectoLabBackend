package com.example.demoProjectoLabBack.persistance.repository;

import com.example.demoProjectoLabBack.persistance.entities.Job;
import com.example.demoProjectoLabBack.persistance.entities.WorkerProfile;
import org.bson.types.ObjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends MongoRepository<WorkerProfile, String> {
    // Additional query methods (if needed) can be defined here
    Optional<WorkerProfile> findByUserId(String userId);

    //List<WorkerProfile> findByJobs_Id(String jobId);

    List<WorkerProfile> findByJobIdsContaining(String jobId);


}
