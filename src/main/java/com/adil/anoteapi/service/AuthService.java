package com.adil.anoteapi.service;


import com.adil.anoteapi.entity.User;
import com.adil.anoteapi.enums.Role;
import com.adil.anoteapi.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private UserRepository repo;
    private JWTService jwtService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    AuthenticationManager authManager;

    public AuthService(UserRepository repo, AuthenticationManager authManager, JWTService jwtService) {
        this.repo = repo;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public User resgiter(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        return repo.save(user);
    }

    public String verify(User user) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername() != null ? user.getUsername() : user.getEmail(), user.getPassword())
        );
        if (authentication.isAuthenticated()) {
            String username = user.getUsername() != null ? user.getUsername() : user.getEmail();
            User dbUser = repo.findByUsernameOrEmail(username).orElseThrow(() -> new RuntimeException("User not found"));

            return jwtService.generateToken(dbUser.getUsername(), dbUser.getEmail(), dbUser.getRole().name(), dbUser.getId());
        } else {
            return "fail";
        }
    }
}
