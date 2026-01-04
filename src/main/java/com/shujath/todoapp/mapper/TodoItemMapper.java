package com.shujath.todoapp.mapper;

import com.shujath.todoapp.dto.todoitem.TodoItemResponse;
import com.shujath.todoapp.entity.TodoItem;
import org.springframework.stereotype.Component;

@Component
public class TodoItemMapper {

    public TodoItemResponse toResponse(TodoItem item) {
        return TodoItemResponse.builder()
                .id(item.getId())
                .title(item.getTitle())
                .status(item.getStatus().name())
                .deadline(item.getDeadline())
                .build();
    }
}
