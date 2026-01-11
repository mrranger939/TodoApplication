package com.shujath.todoapp.service.impl;

import com.shujath.todoapp.dto.todolist.CreateTodoListRequest;
import com.shujath.todoapp.dto.todolist.TodoListResponse;
import com.shujath.todoapp.dto.todolist.UpdateTodoListRequest;
import com.shujath.todoapp.entity.TodoList;
import com.shujath.todoapp.entity.User;
import com.shujath.todoapp.mapper.TodoListMapper;
import com.shujath.todoapp.repository.TodoListRepository;
import com.shujath.todoapp.repository.UserRepository;
import com.shujath.todoapp.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoListServiceImpl implements TodoListService {

    private final UserRepository userRepository;
    private final TodoListRepository todoListRepository;
    private final TodoListMapper todoListMapper;

    @Override
    public TodoListResponse createTodoList(Long userId, CreateTodoListRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        TodoList todoList = TodoList.builder()
                .name(request.getName())
                .user(user)
                .build();

        TodoList saved = todoListRepository.save(todoList);

        return todoListMapper.toResponse(saved);
    }

    @Override
    public List<TodoListResponse> getAllTodoLists(Long userId) {

        List<TodoList> todoLists = todoListRepository.findByUserId(userId);

        return todoListMapper.toResponseList(todoLists);
    }

    @Override
    public TodoListResponse updateTodoList(Long userId, Long listId, UpdateTodoListRequest request) {

        TodoList todoList = todoListRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("Todo list not found"));

        if (!todoList.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to update this todo list");
        }
        // Update only allowed field
        todoList.setName(request.getName());

        TodoList updated = todoListRepository.save(todoList);

        return todoListMapper.toResponse(updated);
    }

    @Override
    public TodoListResponse deleteTodoList(Long userId, Long listId) {

        TodoList todoList = todoListRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("Todo list not found"));

        if (!todoList.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to delete this todo list");
        }

        // Convert to DTO BEFORE deleting
        TodoListResponse response = todoListMapper.toResponse(todoList);

        // Delete (cascade will remove items)
        todoListRepository.delete(todoList);

        return response;
    }

}
