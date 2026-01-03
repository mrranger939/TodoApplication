package com.shujath.todoapp.service;

import com.shujath.todoapp.dto.todolist.CreateTodoListRequest;
import com.shujath.todoapp.dto.todolist.TodoListResponse;

import java.util.List;

public interface TodoListService {
    TodoListResponse createTodoList(CreateTodoListRequest request);
    List<TodoListResponse> getAllTodoLists(Long userId);
}

