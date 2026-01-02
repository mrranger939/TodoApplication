package com.shujath.todoapp.repository;

import com.shujath.todoapp.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
}
