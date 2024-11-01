package com.minicurso.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minicurso.todo.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
