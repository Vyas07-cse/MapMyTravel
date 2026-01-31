package com.personal.demo.Controller;

import org.jspecify.annotations.Nullable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.personal.demo.Dto.UserDto;



import org.springframework.security.crypto.password.PasswordEncoder;

import com.personal.demo.Entity.User;
import com.personal.demo.Repositories.UserRepo;
import com.personal.demo.Service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    public UserController(UserService userService, PasswordEncoder passwordEncoder, UserRepo userRepo) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    // Create user
    @PostMapping("/register")
    public UserDto create(@RequestBody UserDto userDto) {
    // 1. Map DTO to Entity
    User user = new User();
    user.setName(userDto.getName());
    user.setEmail(userDto.getEmail());
    user.setHomeAddress(userDto.getHomeAddress());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    
    // 3. Save Entity
    User savedUser = userRepo.save(user);
    
    // 4. Map saved Entity back to DTO for the response
    userDto.setId(savedUser.getId());
    return userDto;
}


    // Read single user
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

 
    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}