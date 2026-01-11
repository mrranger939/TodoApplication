package com.shujath.todoapp.controller;


import com.shujath.todoapp.dto.todolist.CreateTodoListRequest;
import com.shujath.todoapp.dto.todolist.TodoListResponse;
import com.shujath.todoapp.dto.todolist.UpdateTodoListRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
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
    public TodoListResponse createTodoList(@RequestBody CreateTodoListRequest request, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return todoListService.createTodoList(userId, request);
    }

    // Get all todo lists of current user
    // GET /api/v1/lists?userId=1
    @GetMapping
    public List<TodoListResponse> getAllTodoLists(Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return todoListService.getAllTodoLists(userId);
    }



    // Update todo list name
    @PutMapping("/{listId}")
    public TodoListResponse updateTodoList(
            @PathVariable Long listId,
            @RequestBody UpdateTodoListRequest request,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        return todoListService.updateTodoList(userId, listId, request);
    }

    // Delete todo list
    @DeleteMapping("/{listId}")
    public TodoListResponse deleteTodoList(@PathVariable Long listId, Authentication authentication) {
        Long userId = (Long) authentication.getPrincipal();
        return todoListService.deleteTodoList(userId, listId);
    }
}