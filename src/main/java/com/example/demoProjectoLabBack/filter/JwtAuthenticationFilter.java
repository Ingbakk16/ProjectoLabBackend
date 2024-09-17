package com.example.demoProjectoLabBack.filter;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = getJwtFromRequest(request);

        if (token != null && validateToken(token)) {
            String userId = getUserIdFromToken(token);  // Extract userId from the token

            // Create authentication object and set it in the security context
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userId, null, null);  // No authorities for now
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);  // Remove "Bearer " prefix
        }
        return null;
    }

    private boolean validateToken(String token) {
        // Implement JWT validation logic here (e.g., check signature, expiration)
        return true;
    }

    private String getUserIdFromToken(String token) {
        // Parse JWT token and extract userId (subject) from claims
        return Jwts.parser()
                .setSigningKey("your-secret-key")  // Use your secret key
                .parseClaimsJws(token)
                .getBody()
                .getSubject();  // Assuming userId is the subject
    }
}
