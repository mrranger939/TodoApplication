package com.shujath.todoapp.service.impl;

import com.shujath.todoapp.dto.todoitem.CreateTodoItemRequest;
import com.shujath.todoapp.dto.todoitem.TodoItemResponse;
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
    public TodoItemResponse createTodoItem(Long listId, CreateTodoItemRequest request) {

        // 1️⃣ listId → TodoList
        TodoList todoList = todoListRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("Todo list not found"));

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
    public List<TodoItemResponse> getAllTodoItems(Long listId) {

        // Optional safety check (recommended)
        todoListRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("Todo list not found"));

        List<TodoItem> items = todoItemRepository.findByTodoListId(listId);

        return todoItemMapper.toResponseList(items);
    }

}
