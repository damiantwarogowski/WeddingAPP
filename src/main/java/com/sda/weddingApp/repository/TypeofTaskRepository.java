package com.sda.weddingApp.repository;

import com.sda.weddingApp.model.TypeOfTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeofTaskRepository extends JpaRepository<TypeOfTask, Long> {
    Optional<TypeOfTask> findByName(String task);
}
