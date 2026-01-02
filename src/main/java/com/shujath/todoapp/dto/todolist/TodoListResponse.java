package com.shujath.todoapp.dto.todolist;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TodoListResponse {
    private Long id;
    private String name;
}
