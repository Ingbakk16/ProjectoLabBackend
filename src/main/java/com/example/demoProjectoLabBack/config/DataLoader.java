package com.example.demoProjectoLabBack.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demoProjectoLabBack.model.enums.RoleName;
import com.example.demoProjectoLabBack.persistance.entities.Job;
import com.example.demoProjectoLabBack.persistance.entities.Role;
import com.example.demoProjectoLabBack.persistance.repository.JobRepository;
import com.example.demoProjectoLabBack.persistance.repository.RoleRepository;

@Configuration
public class DataLoader {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JobRepository jobRepository;

    @Bean
    public CommandLineRunner loadData() {
        return args -> {
            if (roleRepository.count() == 0) {
                Role userRole = new Role();
                userRole.setName(RoleName.ROLE_USER);
                roleRepository.save(userRole);

                Role adminRole = new Role();
                adminRole.setName(RoleName.ROLE_ADMIN);
                roleRepository.save(adminRole);

                Role workerRole = new Role();
                workerRole.setName(RoleName.ROLE_WORKER);
                roleRepository.save(workerRole);
            }

            if (jobRepository.count() == 0) {
                Job gardenerJob = new Job();
                gardenerJob.setTitle("Gardener");
                gardenerJob.setDescription("Handles all gardening tasks");
                jobRepository.save(gardenerJob);

                Job electricianJob = new Job();
                electricianJob.setTitle("Electrician");
                electricianJob.setDescription("Handles all electrical tasks");
                jobRepository.save(electricianJob);
            }
        };
    }
}
