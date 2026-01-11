package com.shujath.todoapp.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class ErrorResponse {

    private String message;
    private int status;
    private Instant timestamp;
}
