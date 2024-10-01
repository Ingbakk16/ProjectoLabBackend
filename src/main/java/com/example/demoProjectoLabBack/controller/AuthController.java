package com.example.demoProjectoLabBack.controller;

import com.example.demoProjectoLabBack.Util.JwtResponse;
import com.example.demoProjectoLabBack.Util.JwtTokenUtil;
import com.example.demoProjectoLabBack.Util.JwtUserDetails;
import com.example.demoProjectoLabBack.model.dto.LoginRequestDto;
import com.example.demoProjectoLabBack.model.dto.LoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            // Set the authentication object in the context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Cast to get the JwtUserDetails object
            JwtUserDetails userDetails = (JwtUserDetails) authentication.getPrincipal();

            // Get the userId from JwtUserDetails
            String userId = userDetails.getId();

            // Generate JWT token including userId
            String jwt = jwtUtil.generateToken(userDetails, userId);

            // Return the JWT response
            return ResponseEntity.ok(new JwtResponse(jwt));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}



