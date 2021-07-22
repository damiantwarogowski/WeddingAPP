package com.sda.weddingApp.service;

import com.sda.weddingApp.model.TaskCost;
import com.sda.weddingApp.model.TaskToDo;
import com.sda.weddingApp.repository.CostRepository;
import com.sda.weddingApp.repository.TaskToDoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CostService {
    private final CostRepository costRepository;
    private final TaskToDoRepository taskToDoRepository;

    public List<TaskCost> findAll() {
        return costRepository.findAll();
    }

    public Optional<TaskCost> findCostOfTask(Long id) {
        return costRepository.findById(id);
    }

    public void addCostToTask(TaskCost taskCost, Long taskId) {
        Optional<TaskToDo> taskOptional = taskToDoRepository.findById(taskId);
        if (taskOptional.isPresent()) {
            TaskToDo task = taskOptional.get();
            task.getCosts().add(taskCost);
            taskToDoRepository.save(task);
        }
    }
}
