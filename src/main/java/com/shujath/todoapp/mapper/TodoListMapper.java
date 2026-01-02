package com.shujath.todoapp.mapper;

import com.shujath.todoapp.dto.todolist.TodoListResponse;
import com.shujath.todoapp.entity.TodoList;
import org.springframework.stereotype.Component;

@Component
public class TodoListMapper {

    public TodoListResponse toResponse(TodoList todoList) {
        return TodoListResponse.builder()
                .id(todoList.getId())
                .name(todoList.getName())
                .build();
    }
}
