package com.example.demoProjectoLabBack.Util;

import com.example.demoProjectoLabBack.persistance.entities.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class JwtUserDetails implements UserDetails {

    private String id;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String email;
    private Role role;

    // Constructor
    public JwtUserDetails(String id, String username, String password, String name,
                          String lastname, String email, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.role = role;
    }

    // Additional Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Check for null role to avoid NullPointerException
        if (role == null || role.getName() == null) {
            throw new IllegalStateException("User role is not set");
        }

        // Convert the role into a SimpleGrantedAuthority for Spring Security
        return Collections.singletonList(new SimpleGrantedAuthority(role.getName().name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Customize based on your requirements (e.g., add an expiration date field)
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Customize based on your requirements (e.g., add a field for account lock status)
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Customize based on your requirements (e.g., add password expiration logic)
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Customize based on your requirements (e.g., add an "enabled" field)
        return true;
    }
}
