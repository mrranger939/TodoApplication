package com.shujath.todoapp.controller;


import com.shujath.todoapp.dto.todolist.CreateTodoListRequest;
import com.shujath.todoapp.dto.todolist.TodoListResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.shujath.todoapp.service.TodoListService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/lists")
public class TodoListController {

    private final TodoListService todoListService;

    // Create todo list
    @PostMapping
    public TodoListResponse createTodoList(@RequestBody CreateTodoListRequest request) {
        return todoListService.createTodoList(request);
    }

    // Get all todo lists of current user
    // GET /api/v1/lists?userId=1
    @GetMapping
    public List<TodoListResponse> getAllTodoLists(@RequestParam Long userId) {
        return todoListService.getAllTodoLists(userId);
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