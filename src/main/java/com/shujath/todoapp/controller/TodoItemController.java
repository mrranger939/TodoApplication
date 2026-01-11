package com.shujath.todoapp.controller;


import com.shujath.todoapp.dto.todoitem.CreateTodoItemRequest;
import com.shujath.todoapp.dto.todoitem.TodoItemResponse;
import com.shujath.todoapp.dto.todoitem.UpdateTodoItemRequest;
import com.shujath.todoapp.service.TodoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lists/{listId}/items")
public class TodoItemController {

    private final TodoItemService todoItemService;

    // Create todo item
    @PostMapping
    public TodoItemResponse createTodoItem(
            @PathVariable Long listId,
            @RequestBody CreateTodoItemRequest request,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        return todoItemService.createTodoItem(userId, listId, request);
    }

    // Get all items in a list
    @GetMapping
    public List<TodoItemResponse> getAllTodoItems(
            @PathVariable Long listId,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        return todoItemService.getAllTodoItems(userId, listId);
    }

    // Get one todo item
    @GetMapping("/{itemId}")
    public TodoItemResponse getTodoItem(
            @PathVariable Long listId,
            @PathVariable Long itemId,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        return todoItemService.getTodoItem(userId, listId, itemId);
    }

    // Update todo item
    @PutMapping("/{itemId}")
    public TodoItemResponse updateTodoItem(
            @PathVariable Long listId,
            @PathVariable Long itemId,
            @RequestBody UpdateTodoItemRequest request,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        return todoItemService.updateTodoItem(userId, listId, itemId, request);
    }

    // Delete todo item
    @DeleteMapping("/{itemId}")
    public TodoItemResponse deleteTodoItem(
            @PathVariable Long listId,
            @PathVariable Long itemId,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        return todoItemService.deleteTodoItem(userId, listId, itemId);
    }
}
