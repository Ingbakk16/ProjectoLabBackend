package com.example.demoProjectoLabBack.service;

import com.example.demoProjectoLabBack.model.dto.UserDto;
import com.example.demoProjectoLabBack.model.dto.UserForEditDto;
import com.example.demoProjectoLabBack.model.enums.RoleName;
import com.example.demoProjectoLabBack.persistance.entities.Role;
import com.example.demoProjectoLabBack.persistance.entities.User;
import com.example.demoProjectoLabBack.persistance.entities.WorkerProfile;
import com.example.demoProjectoLabBack.persistance.repository.RoleRepository;
import com.example.demoProjectoLabBack.persistance.repository.UserRepository;
import com.example.demoProjectoLabBack.persistance.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private RoleRepository roleRepository;



    public User createUser(User user) {
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    public User findUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }






    public boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean isEmailTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }





    public List<User> findAllUsersWithUserRole() {

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Role not found"));


        return userRepository.findAllByRoleId(userRole.getId());
    }

    public List<User> findAllUsersWithWorkerRole() {
        Role userRole = roleRepository.findByName(RoleName.ROLE_WORKER)
                .orElseThrow(() -> new RuntimeException("Role not found"));


        return userRepository.findAllByRoleId(userRole.getId());
    }

    public void deleteUser(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        workerRepository.deleteByUser(user);

        userRepository.deleteById(String.valueOf(userId));
    }

    public UserDto updateUserProfile(String userId, UserDto updateData) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));


        user.setUsername(updateData.getUsername());
        user.setName(updateData.getName());
        user.setLastname(updateData.getLastname());
        user.setEmail(updateData.getEmail());
        // Add any other fields you want to update


        User updatedUser = userRepository.save(user);

        return convertToDto(updatedUser);  // Return updated user DTO
    }


    private UserDto convertToDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getName(), user.getLastname(), user.getEmail());
    }


    public void updateUserRole(String userId, RoleName roleName) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        Role newRole = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));


        user.setRole(newRole);


        userRepository.save(user);
    }

}