package com.shujath.todoapp.repository;

import com.shujath.todoapp.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    List<TodoItem> findByTodoListId(Long listId);
    Optional<TodoItem> findByIdAndTodoListId(Long itemId, Long listId);
}
