package com.shujath.todoapp.controller;

import com.shujath.todoapp.dto.todolist.CreateTodoListRequest;
import com.shujath.todoapp.dto.todolist.TodoListResponse;
import com.shujath.todoapp.dto.todolist.UpdateTodoListRequest;
import com.shujath.todoapp.service.TodoListService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/lists")
public class TodoListController {

    private final TodoListService todoListService;

    // Create todo list
    @PostMapping
    public ResponseEntity<TodoListResponse> createTodoList(
            @RequestBody CreateTodoListRequest request,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        TodoListResponse response = todoListService.createTodoList(userId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response); // 201
    }

    // Get all todo lists of current user
    @GetMapping
    public ResponseEntity<List<TodoListResponse>> getAllTodoLists(
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        List<TodoListResponse> response = todoListService.getAllTodoLists(userId);

        return ResponseEntity.ok(response); // 200
    }

    // Update todo list name
    @PutMapping("/{listId}")
    public ResponseEntity<TodoListResponse> updateTodoList(
            @PathVariable Long listId,
            @RequestBody UpdateTodoListRequest request,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        TodoListResponse response =
                todoListService.updateTodoList(userId, listId, request);

        return ResponseEntity.ok(response); // 200
    }

    // Delete todo list
    @DeleteMapping("/{listId}")
    public ResponseEntity<TodoListResponse> deleteTodoList(
            @PathVariable Long listId,
            Authentication authentication
    ) {
        Long userId = (Long) authentication.getPrincipal();
        TodoListResponse response =
                todoListService.deleteTodoList(userId, listId);

        return ResponseEntity.ok(response); // 200
    }
}
