package com.shujath.todoapp.repository;

import com.shujath.todoapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
