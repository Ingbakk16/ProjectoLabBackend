package com.example.demoProjectoLabBack.service;

import com.example.demoProjectoLabBack.Util.JwtUserDetails;
import com.example.demoProjectoLabBack.persistance.entities.User;
import com.example.demoProjectoLabBack.persistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // Your repository for user data

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Query the database and handle the Optional
        Optional<User> optionalUser = userRepository.findByUsername(username); // This returns Optional<User>

        // Check if the user is present
        if (!optionalUser.isPresent()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = optionalUser.get(); // Get the User object from the Optional
        return new JwtUserDetails(user.getId(), user.getUsername(), user.getPassword(), user.getName(), user.getLastname(), user.getEmail(), user.getRole());
    }
}



