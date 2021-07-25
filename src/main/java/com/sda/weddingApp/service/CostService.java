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
//    public void addCostTOTask(Long weddingId, Long typeOfCostId, TaskCost taskCost,  double bailCost,LocalDate bailCostDeadline, double totalCost,LocalDate totalCostDeadline) {
//        //
//        TypeOfCo taskType = null;
//        // czy użytkownik wpisał własną nazwę taska?
//        if (ownTypeOfTask == null || ownTypeOfTask.isEmpty()) {
//            // nie
//            Optional<TypeOfTask> optionalTaskType = typeofTaskRepository.findById(typeOfTaskId);
//            if (optionalTaskType.isPresent()) {
//                taskType = optionalTaskType.get();
//
//
//            }
//        } else{
//            Optional<TypeOfTask> optionalTaskType = typeofTaskRepository.findAll()
//                    .stream()
//                    .filter(tTask -> tTask.getName().equalsIgnoreCase(ownTypeOfTask))
//                    .findAny();
//
//            // gościu wpisał własne wejście
//            if (optionalTaskType.isPresent()) {
//                // znaleźliśmy
//                taskType = optionalTaskType.get();
//            }else{
//                // nie znaleźliśmy
//                // 1. tworzymy nowy task
//                TypeOfTask doDodania = TypeOfTask.builder().name(ownTypeOfTask).build();
//                // 2. dodajemy go do bazy
//                taskType = typeofTaskRepository.save(doDodania);
//
//            }
//
//        }
//        if( taskType == null){
//            throw new UnsupportedOperationException("Unexpected error.");
//        }
//
//        Optional<Wedding> optionalWedding = weddingRepository.findById(weddingId);
//        if (optionalWedding.isPresent()) {
//            Wedding wedding = optionalWedding.get();
//
//            TaskToDo taskToDo = TaskToDo.builder()
//                    .typeOfTask(taskType)
//                    .deadlineDay(task.getDeadlineDay())
//                    .wedding(wedding)
//                    .build();
//
//            taskToDoRepository.save(taskToDo);
//        }
//    }

}

