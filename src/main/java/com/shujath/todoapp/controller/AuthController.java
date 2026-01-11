package com.shujath.todoapp.controller;

import com.shujath.todoapp.dto.auth.LoginRequest;
import com.shujath.todoapp.dto.auth.LoginResponse;
import com.shujath.todoapp.dto.auth.RegisterRequest;
import com.shujath.todoapp.dto.auth.RegisterResponse;
import com.shujath.todoapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    // Register (Sign up)
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @RequestBody RegisterRequest request
    ) {
        RegisterResponse response = userService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED) // 201
                .body(response);
    }

    // Login (Sign in)
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {
        LoginResponse response = userService.login(request);

        return ResponseEntity.ok(response); // 200
    }
}
