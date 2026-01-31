package com.personal.demo.Service;

import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.personal.demo.Entity.User;
import com.personal.demo.Repositories.UserRepo;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user) {
        // Add password hashing here if using JWT auth
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public User updateHomeAddress(Long userId, String newAddress) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setHomeAddress(newAddress);
        return userRepo.save(user);
    }

    public boolean delete(Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
    }
        return false;
}
}