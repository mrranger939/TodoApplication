package com.shujath.todoapp.service;

import com.shujath.todoapp.dto.todoitem.CreateTodoItemRequest;
import com.shujath.todoapp.dto.todoitem.TodoItemResponse;
import com.shujath.todoapp.dto.todoitem.UpdateTodoItemRequest;

import java.util.List;

public interface TodoItemService {
    TodoItemResponse createTodoItem(Long listId, CreateTodoItemRequest request);
    List<TodoItemResponse> getAllTodoItems(Long listId);
    TodoItemResponse getTodoItem(Long listId, Long itemId);
    TodoItemResponse updateTodoItem(
            Long listId,
            Long itemId,
            UpdateTodoItemRequest request
    );

    TodoItemResponse deleteTodoItem(Long listId, Long itemId);
}
