package com.shujath.todoapp.controller;

import com.shujath.todoapp.dto.auth.LoginRequest;
import com.shujath.todoapp.dto.auth.LoginResponse;
import com.shujath.todoapp.dto.auth.RegisterRequest;
import com.shujath.todoapp.dto.auth.RegisterResponse;
import com.shujath.todoapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;

    // Register (Sign up)
    @PostMapping("/register")
    public RegisterResponse register(
            @RequestBody RegisterRequest request
    ) {
        return userService.register(request);
    }

    // Login (Sign in)
    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request
    ) {
        return userService.login(request);
    }
}
