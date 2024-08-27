package com.example.demoProjectoLabBack.persistance.repository;


import com.example.demoProjectoLabBack.model.enums.RoleName;
import com.example.demoProjectoLabBack.persistance.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleName name);
}
