package com.shujath.todoapp.service.impl;

import com.shujath.todoapp.dto.auth.LoginRequest;
import com.shujath.todoapp.dto.auth.LoginResponse;
import com.shujath.todoapp.dto.auth.RegisterRequest;
import com.shujath.todoapp.dto.auth.RegisterResponse;
import com.shujath.todoapp.dto.user.UserProfileResponse;
import com.shujath.todoapp.entity.User;
import com.shujath.todoapp.repository.UserRepository;
import com.shujath.todoapp.security.JwtService;
import com.shujath.todoapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public RegisterResponse register(RegisterRequest request) {

        // 1️⃣ Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // 2️⃣ Hash password
        String hashedPassword = passwordEncoder.encode(request.getPassword());

        // 3️⃣ Create User entity
        User user = User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(hashedPassword)
                .build();

        // 4️⃣ Save user
        User savedUser = userRepository.save(user);

        // 5️⃣ Return response (no password)
        return RegisterResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .username(savedUser.getUsername())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        // 1️⃣ Find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // 2️⃣ Verify password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // 3️⃣ Generate JWT
        String token = jwtService.generateToken(user.getId(), user.getEmail());

        // 4️⃣ Return token
        return LoginResponse.builder()
                .accessToken(token)
                .build();
    }

    @Override
    public UserProfileResponse getCurrentUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserProfileResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }

}
