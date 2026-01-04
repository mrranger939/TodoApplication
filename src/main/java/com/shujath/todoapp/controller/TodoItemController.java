package com.shujath.todoapp.controller;


import com.shujath.todoapp.dto.todoitem.CreateTodoItemRequest;
import com.shujath.todoapp.dto.todoitem.TodoItemResponse;
import com.shujath.todoapp.service.TodoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public void getAllTodoItems(
            @PathVariable Long listId
    ) {
        // TODO: implement later
    }

    // Get one todo item
    @GetMapping("/{itemId}")
    public void getTodoItem(
            @PathVariable Long listId,
            @PathVariable Long itemId
    ) {
        // TODO: implement later
    }

    // Update todo item
    @PutMapping("/{itemId}")
    public void updateTodoItem(
            @PathVariable Long listId,
            @PathVariable Long itemId
    ) {
        // TODO: implement later
    }

    // Delete todo item
    @DeleteMapping("/{itemId}")
    public void deleteTodoItem(
            @PathVariable Long listId,
            @PathVariable Long itemId
    ) {
        // TODO: implement later
    }
}
