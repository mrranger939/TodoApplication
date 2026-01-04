package com.shujath.todoapp.service;

import com.shujath.todoapp.dto.todoitem.CreateTodoItemRequest;
import com.shujath.todoapp.dto.todoitem.TodoItemResponse;

public interface TodoItemService {
    TodoItemResponse createTodoItem(Long listId, CreateTodoItemRequest request);
}
