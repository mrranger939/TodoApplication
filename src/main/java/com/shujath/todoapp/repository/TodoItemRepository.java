package com.shujath.todoapp.repository;

import com.shujath.todoapp.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    List<TodoItem> findByTodoListId(Long listId);
}
