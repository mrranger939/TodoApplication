package com.shujath.todoapp.service.impl;

import com.shujath.todoapp.dto.todoitem.CreateTodoItemRequest;
import com.shujath.todoapp.dto.todoitem.TodoItemResponse;
import com.shujath.todoapp.dto.todoitem.UpdateTodoItemRequest;
import com.shujath.todoapp.entity.TodoItem;
import com.shujath.todoapp.entity.TodoList;
import com.shujath.todoapp.mapper.TodoItemMapper;
import com.shujath.todoapp.repository.TodoItemRepository;
import com.shujath.todoapp.repository.TodoListRepository;
import com.shujath.todoapp.service.TodoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoItemServiceImpl implements TodoItemService {

    private final TodoItemRepository todoItemRepository;
    private final TodoListRepository todoListRepository;
    private final TodoItemMapper todoItemMapper;

    @Override
    public TodoItemResponse createTodoItem(Long userId, Long listId, CreateTodoItemRequest request) {

        // 1️⃣ listId → TodoList
        TodoList todoList = todoListRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("Todo list not found"));

        if (!todoList.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to add items to this list");
        }

        // 2️⃣ Build TodoItem
        TodoItem todoItem = TodoItem.builder()
                .title(request.getTitle())
                .status(TodoItem.Status.valueOf(request.getStatus()))
                .deadline(request.getDeadline())
                .todoList(todoList)
                .build();

        // 3️⃣ Save
        TodoItem saved = todoItemRepository.save(todoItem);

        return todoItemMapper.toResponse(saved);
    }

    @Override
    public List<TodoItemResponse> getAllTodoItems(Long userId, Long listId) {

        // Optional safety check (recommended)
        TodoList todoList = todoListRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("Todo list not found"));

        if (!todoList.getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to add items to this list");
        }

        List<TodoItem> items = todoItemRepository.findByTodoListId(listId);

        return todoItemMapper.toResponseList(items);
    }

    @Override
    public TodoItemResponse getTodoItem(Long userId, Long listId, Long itemId) {

        TodoItem item = todoItemRepository
                .findByIdAndTodoListId(itemId, listId)
                .orElseThrow(() ->
                        new RuntimeException("Todo item not found for this list")
                );

        if (!item.getTodoList().getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to view this todo item");
        }
        return todoItemMapper.toResponse(item);
    }

    @Override
    public TodoItemResponse updateTodoItem(
            Long userId,
            Long listId,
            Long itemId,
            UpdateTodoItemRequest request
    ) {

        TodoItem item = todoItemRepository
                .findByIdAndTodoListId(itemId, listId)
                .orElseThrow(() ->
                        new RuntimeException("Todo item not found for this list")
                );

        if (!item.getTodoList().getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to update this todo item");
        }

        // Update only provided fields
        if (request.getTitle() != null) {
            item.setTitle(request.getTitle());
        }

        if (request.getStatus() != null) {
            item.setStatus(TodoItem.Status.valueOf(request.getStatus()));
        }

        if (request.getDeadline() != null) {
            item.setDeadline(request.getDeadline());
        }

        TodoItem updated = todoItemRepository.save(item);

        return todoItemMapper.toResponse(updated);
    }

    @Override
    public TodoItemResponse deleteTodoItem(Long userId, Long listId, Long itemId) {

        TodoItem item = todoItemRepository
                .findByIdAndTodoListId(itemId, listId)
                .orElseThrow(() ->
                        new RuntimeException("Todo item not found for this list")
                );

        if (!item.getTodoList().getUser().getId().equals(userId)) {
            throw new RuntimeException("You are not allowed to update this todo item");
        }
        // Capture response BEFORE delete
        TodoItemResponse response = todoItemMapper.toResponse(item);

        todoItemRepository.delete(item);

        return response;
    }

}
