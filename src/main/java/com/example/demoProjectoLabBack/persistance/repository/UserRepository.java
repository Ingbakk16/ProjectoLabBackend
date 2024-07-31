package com.example.demoProjectoLabBack.persistance.repository;

import com.example.demoProjectoLabBack.persistance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
