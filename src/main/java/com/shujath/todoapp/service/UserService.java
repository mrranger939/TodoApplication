package com.shujath.todoapp.service;

import com.shujath.todoapp.dto.auth.LoginRequest;
import com.shujath.todoapp.dto.auth.LoginResponse;
import com.shujath.todoapp.dto.auth.RegisterRequest;
import com.shujath.todoapp.dto.auth.RegisterResponse;
import com.shujath.todoapp.dto.user.UserProfileResponse;

public interface UserService {

    RegisterResponse register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

    UserProfileResponse getCurrentUser(String email);
}
