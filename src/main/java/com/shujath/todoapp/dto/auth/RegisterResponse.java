package com.shujath.todoapp.dto.auth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterResponse {

    private Long id;
    private String email;
    private String username;
}
