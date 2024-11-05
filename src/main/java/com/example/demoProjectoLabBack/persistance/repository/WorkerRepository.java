package com.example.demoProjectoLabBack.persistance.repository;

import com.example.demoProjectoLabBack.persistance.entities.User;
import com.example.demoProjectoLabBack.persistance.entities.WorkerProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends MongoRepository<WorkerProfile, String> {
    // Additional query methods (if needed) can be defined here
    Optional<WorkerProfile> findByUserId(String userId);

    void deleteByUser(User user);

    List<WorkerProfile> findByJobIdsContaining(String jobId);

    Optional findByDni(Integer dni);

    Optional findByPhoneNumber(long phoneNumber);


}
