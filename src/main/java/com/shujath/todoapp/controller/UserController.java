package com.shujath.todoapp.controller;

import com.shujath.todoapp.dto.user.UserProfileResponse;
import com.shujath.todoapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    // Get current user profile
    @GetMapping("/me")
    public UserProfileResponse getCurrentUser(Authentication authentication) {

        String email = authentication.getPrincipal().toString();

        return userService.getCurrentUser(email);
    }
}
