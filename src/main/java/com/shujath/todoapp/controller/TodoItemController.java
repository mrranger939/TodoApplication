package com.shujath.todoapp.controller;


import com.shujath.todoapp.dto.todoitem.CreateTodoItemRequest;
import com.shujath.todoapp.dto.todoitem.TodoItemResponse;
import com.shujath.todoapp.dto.todoitem.UpdateTodoItemRequest;
import com.shujath.todoapp.service.TodoItemService;
import lombok.RequiredArgsConstructor;
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
            @RequestBody CreateTodoItemRequest request
    ) {

        return todoItemService.createTodoItem(listId, request);
    }

    // Get all items in a list
    @GetMapping
    public List<TodoItemResponse> getAllTodoItems(
            @PathVariable Long listId
    ) {
        return todoItemService.getAllTodoItems(listId);
    }

    // Get one todo item
    @GetMapping("/{itemId}")
    public TodoItemResponse getTodoItem(
            @PathVariable Long listId,
            @PathVariable Long itemId
    ) {
        return todoItemService.getTodoItem(listId, itemId);
    }

    // Update todo item
    @PutMapping("/{itemId}")
    public TodoItemResponse updateTodoItem(
            @PathVariable Long listId,
            @PathVariable Long itemId,
            @RequestBody UpdateTodoItemRequest request
    ) {
        return todoItemService.updateTodoItem(listId, itemId, request);
    }

    // Delete todo item
    @DeleteMapping("/{itemId}")
    public TodoItemResponse deleteTodoItem(
            @PathVariable Long listId,
            @PathVariable Long itemId
    ) {
        return todoItemService.deleteTodoItem(listId, itemId);
    }
}
