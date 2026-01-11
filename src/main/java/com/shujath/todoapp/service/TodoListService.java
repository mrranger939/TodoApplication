package com.shujath.todoapp.service;

import com.shujath.todoapp.dto.todolist.CreateTodoListRequest;
import com.shujath.todoapp.dto.todolist.TodoListResponse;
import com.shujath.todoapp.dto.todolist.UpdateTodoListRequest;

import java.util.List;

public interface TodoListService {
    TodoListResponse createTodoList(Long userId, CreateTodoListRequest request);
    List<TodoListResponse> getAllTodoLists(Long userId);
    TodoListResponse updateTodoList(Long userId,Long listId, UpdateTodoListRequest request);
    TodoListResponse deleteTodoList(Long userId,Long listId);
}

