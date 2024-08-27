package com.example.demoProjectoLabBack.service;

import com.example.demoProjectoLabBack.model.enums.RoleName;
import com.example.demoProjectoLabBack.persistance.entities.Role;
import com.example.demoProjectoLabBack.persistance.entities.User;
import com.example.demoProjectoLabBack.persistance.repository.RoleRepository;
import com.example.demoProjectoLabBack.persistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;



    public User createUser(User user) {
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    public User findUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAllUsersWithUserRole() {
        return userRepository.findAllByRoleName(RoleName.ROLE_USER);
    }

    public List<User> findAllUsersWithWorkerRole() {
        return userRepository.findAllByRoleName(RoleName.ROLE_WORKER);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void updateUserRole(Integer userId, RoleName roleName) {
        // Load the user from the database
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Load the role from the database

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Set the new role for the user
        user.setRole(role);

        // Save the user to persist the role change
        userRepository.save(user);
    }

}