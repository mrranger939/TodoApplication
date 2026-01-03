package com.shujath.todoapp.mapper;

import com.shujath.todoapp.dto.todolist.TodoListResponse;
import com.shujath.todoapp.entity.TodoList;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoListMapper {

    public TodoListResponse toResponse(TodoList todoList) {
        return TodoListResponse.builder()
                .id(todoList.getId())
                .name(todoList.getName())
                .build();
    }

    public List<TodoListResponse> toResponseList(List<TodoList> todoLists) {
        return todoLists.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
