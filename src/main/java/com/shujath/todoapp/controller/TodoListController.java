package com.shujath.todoapp.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lists")
public class TodoListController {

    // Create todo list
    @PostMapping
    public void createTodoList() {
        // TODO: implement later
    }

    // Get all todo lists of current user
    @GetMapping
    public void getAllTodoLists() {
        // TODO: implement later
    }

    // Get one todo list
    @GetMapping("/{listId}")
    public void getTodoList(@PathVariable Long listId) {
        // TODO: implement later
    }

    // Update todo list name
    @PutMapping("/{listId}")
    public void updateTodoList(
            @PathVariable Long listId
    ) {
        // TODO: implement later
    }

    // Delete todo list
    @DeleteMapping("/{listId}")
    public void deleteTodoList(@PathVariable Long listId) {
        // TODO: implement later
    }
}