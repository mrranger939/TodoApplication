package com.shujath.todoapp.service;

import com.shujath.todoapp.dto.todolist.CreateTodoListRequest;
import com.shujath.todoapp.dto.todolist.TodoListResponse;

public interface TodoListService {
    TodoListResponse createTodoList(CreateTodoListRequest request);
}

