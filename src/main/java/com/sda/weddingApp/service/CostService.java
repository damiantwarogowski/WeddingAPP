package com.sda.weddingApp.service;

import com.sda.weddingApp.model.*;
import com.sda.weddingApp.repository.CostRepository;
import com.sda.weddingApp.repository.TaskToDoRepository;
import com.sda.weddingApp.repository.TypeofCostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CostService {
    private final CostRepository costRepository;
    private final TaskToDoRepository taskToDoRepository;
    private final TypeofCostRepository typeofCostRepository;



    public List<TaskCost> findAll() {
        return costRepository.findAll();
    }

    public Optional<TaskCost> findCostOfTask(Long id) {
        return costRepository.findById(id);
    }

    public void addCostToTask(Long taskId, Double bailCost,LocalDate bailCostDeadline, Double totalCost,LocalDate totalCostDeadline ) {
        Optional<TypeOfCost> optionalTypeOfCost = typeofCostRepository.findById(taskId);
        if (optionalTypeOfCost.isPresent()) {
            TypeOfCost typeOfCost= optionalTypeOfCost.get();

            TaskCost taskCost = TaskCost.builder()
                    .typeOfCost(typeOfCost)
                    .bailCost(bailCost)
                    .bailCostDeadline(bailCostDeadline)
                    .totalCost(totalCost)
                    .totalCostDeadline(totalCostDeadline)
                    .build();

            costRepository.save(taskCost);
        }
        log.info("Cost added.");
    }
    public void removeCost(Long id) {
        costRepository.deleteById(id);
        log.info("Cost removed.");
    }

}

