package com.personal.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.demo.Dto.UserDto;
import com.personal.demo.Entity.User;
import com.personal.demo.Repositories.UserRepo;
import com.personal.demo.Service.UserService;
import com.personal.demo.config.JwtUtil;



@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, PasswordEncoder passwordEncoder, UserRepo userRepo, JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;

    }

    // Create user
    @PostMapping("/register")
    public UserDto create(@RequestBody UserDto userDto) {
        if(userRepo.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("User with email " + userDto.getEmail() + " already exists");
        }
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
@GetMapping("/login")
public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
    if(userRepo.existsByEmail(email)) {
        User user = userRepo.findByEmail(email);
        if (passwordEncoder.matches(password, user.getPassword())) {
            String jwt=this.jwtUtil.generateToken(user.getName());
            return ResponseEntity.ok(jwt);
        } else {
            return ResponseEntity.status(401).body("Invalid password");
        }
    } else {
        return ResponseEntity.status(404).body("User not found");
    }
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