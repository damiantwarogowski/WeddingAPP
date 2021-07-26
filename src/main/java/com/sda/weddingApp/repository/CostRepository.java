package com.sda.weddingApp.repository;

import com.sda.weddingApp.model.TaskCost;
import com.sda.weddingApp.model.TypeOfTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CostRepository extends JpaRepository<TaskCost, Long> {
//    Optional<TaskCost> getAllCosts (Double costs);
}
