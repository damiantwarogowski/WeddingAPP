package com.sda.weddingApp.repository;

import com.sda.weddingApp.model.TaskCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostRepository extends JpaRepository<TaskCost, Long> {
}
