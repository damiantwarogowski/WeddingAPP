package com.sda.weddingApp.repository;

import com.sda.weddingApp.model.TypeOfCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeofCostRepository extends JpaRepository<TypeOfCost, Long> {
    Optional<TypeOfCost> findByName(String cost);
}
