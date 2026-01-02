package com.shujath.todoapp.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lists/{listId}/items")
public class TodoItemController {

    // Create todo item
    @PostMapping
    public void createTodoItem(
            @PathVariable Long listId
    ) {
        // TODO: implement later
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
