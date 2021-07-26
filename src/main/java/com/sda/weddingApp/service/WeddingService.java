package com.sda.weddingApp.service;

import com.sda.weddingApp.model.*;
import com.sda.weddingApp.model.dto.SurveyAnswers;
import com.sda.weddingApp.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.sda.weddingApp.configuration.TaskNames.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeddingService {
    private final WeddingFormRepository weddingRepository;
    private final AccountRepository accountRepository;
    private final TypeofTaskRepository typeofTaskRepository;
    private final TaskToDoRepository taskToDoRepository;
    private final CoupleRepository coupleRepository;
    private final CostRepository costRepository;
    private final TypeofCostRepository typeofCostRepository;

    public void createWedding(SurveyAnswers answers, Long accountId) {
        Account account = accountRepository.getById(accountId);

        Couple couple = new Couple(null, answers.getPerson1(), answers.getPerson2(), null);
        couple = coupleRepository.save(couple);


        Wedding wedding = Wedding.builder()
                .dateOfWedding(answers.getWeddingDate())
                .timeOfWedding(answers.getWeddingTime())
                .timeOfWeddingParty(answers.getWeddingPartyTime())
                .couple(couple)
                .owner(account)
                .build();
        wedding = weddingRepository.save(wedding);
        log.info("Wedding added.");


        if (answers.isTaskBand()) {
            addTaskToWedding(wedding, TASK_BAND, answers.getWeddingDate());
        }
        if (answers.isTaskDJ()) {
            addTaskToWedding(wedding, TASK_DJ, answers.getWeddingDate());
        }
        if (answers.isTaskVenue()) {
            addTaskToWedding(wedding, TASK_VENUE, answers.getWeddingDate());
        }
        if (answers.isTaskAuto()) {
            addTaskToWedding(wedding, TASK_AUTO, answers.getWeddingDate());
        }
        if (answers.isTaskAlcohol()) {
            addTaskToWedding(wedding, TASK_ALCOHOL, answers.getWeddingDate());
        }
        if (answers.isTaskInvitations()) {
            addTaskToWedding(wedding, TASK_INVITATIONS, answers.getWeddingDate());
        }
        if (answers.isTaskPhotographer()) {
            addTaskToWedding(wedding, TASK_PHOTOGRAPHER, answers.getWeddingDate());
        }
        if (answers.isTaskRingsTogether()) {
            addTaskToWedding(wedding, TASK_RINGS, answers.getWeddingDate());
        }

        if (answers.isTaskRingsSeparately()) {
            addTaskToWedding(wedding, TASK_RINGS1, answers.getWeddingDate());
            addTaskToWedding(wedding, TASK_RINGS2, answers.getWeddingDate());
        }

        if (answers.isTaskOutfit1()) {
            addTaskToWedding(wedding, TASK_OUTFIT1, answers.getWeddingDate());
        }
        if (answers.isTaskHairdresser1()) {
            addTaskToWedding(wedding, TASK_HAIRDRESSER1, answers.getWeddingDate());
        }
        if (answers.isTaskBarber1()) {
            addTaskToWedding(wedding, TASK_BARBER1, answers.getWeddingDate());
        }
        if (answers.isTaskMakeupArtist1()) {
            addTaskToWedding(wedding, TASK_MAKEUP_ARTIST1, answers.getWeddingDate());
        }
        if (answers.isTaskManiPediCure1()) {
            addTaskToWedding(wedding, TASK_MANI_PEDI_CURE1, answers.getWeddingDate());
        }

        if (answers.isTaskOutfit2()) {
            addTaskToWedding(wedding, TASK_OUTFIT2, answers.getWeddingDate());
        }
        if (answers.isTaskHairdresser2()) {
            addTaskToWedding(wedding, TASK_HAIRDRESSER2, answers.getWeddingDate());
        }
        if (answers.isTaskBarber2()) {
            addTaskToWedding(wedding, TASK_BARBER2, answers.getWeddingDate());
        }
        if (answers.isTaskMakeupArtist2()) {
            addTaskToWedding(wedding, TASK_MAKEUP_ARTIST2, answers.getWeddingDate());
        }
        if (answers.isTaskManiPediCure2()) {
            addTaskToWedding(wedding, TASK_MANI_PEDI_CURE2, answers.getWeddingDate());
        }

    }

    private void addTaskToWedding(Wedding wedding, String name, LocalDate date) {
        Optional<TypeOfTask> optionalTaskType = typeofTaskRepository.findByName(name);
        if (optionalTaskType.isPresent()) {
            TypeOfTask taskType = optionalTaskType.get();

            TaskToDo taskToDo = TaskToDo.builder()
                    .typeOfTask(taskType)
                    .deadlineDay(date)
                    .wedding(wedding)
                    .build();

            taskToDoRepository.save(taskToDo);
        }
    }

    public void addTaskToWedding(Long weddingId, Long typeOfTaskId, TaskToDo task, String ownTypeOfTask, Double plannedCost) {
        //
        TypeOfTask taskType = null;
        // czy użytkownik wpisał własną nazwę taska?
        if (ownTypeOfTask == null || ownTypeOfTask.isEmpty()) {
            // nie
            Optional<TypeOfTask> optionalTaskType = typeofTaskRepository.findById(typeOfTaskId);
            if (optionalTaskType.isPresent()) {
                taskType = optionalTaskType.get();


            }
        } else{
            Optional<TypeOfTask> optionalTaskType = typeofTaskRepository.findAll()
                    .stream()
                    .filter(tTask -> tTask.getName().equalsIgnoreCase(ownTypeOfTask))
                    .findAny();

            // gościu wpisał własne wejście
            if (optionalTaskType.isPresent()) {
                // znaleźliśmy
                taskType = optionalTaskType.get();
            }else{
                // nie znaleźliśmy
                // 1. tworzymy nowy task
                TypeOfTask doDodania = TypeOfTask.builder()
                        .name(ownTypeOfTask)
                        .build();
                // 2. dodajemy go do bazy
                taskType = typeofTaskRepository.save(doDodania);

            }

        }
        if( taskType == null){
            throw new UnsupportedOperationException("Unexpected error.");
        }

        Optional<Wedding> optionalWedding = weddingRepository.findById(weddingId);
        if (optionalWedding.isPresent()) {
            Wedding wedding = optionalWedding.get();

            TaskToDo taskToDo = TaskToDo.builder()
                    .typeOfTask(taskType)
                    .deadlineDay(task.getDeadlineDay())
                    .wedding(wedding)
                    .build();

            taskToDoRepository.save(taskToDo);
        }
    }

    public List<Wedding> getAll() {
        return weddingRepository.findAll();
    }

    public void removeWedding(Long id) {
        weddingRepository.deleteById(id);
    }


    public Optional<Wedding> getWeddingWithId(Long id) {
        return weddingRepository.findById(id);
    }

    public void editWedding(Wedding weddingInfo) {
        Optional<Wedding> weddingOptional = weddingRepository.findById(weddingInfo.getId());
        if(weddingOptional.isPresent()) {
            Wedding wedding = weddingOptional.get();
            wedding.setDateOfWedding(weddingInfo.getDateOfWedding());
            wedding.setTimeOfWedding(weddingInfo.getTimeOfWedding());
            wedding.setTimeOfWeddingParty(weddingInfo.getTimeOfWeddingParty());
//            wedding.setCouple(weddingInfo.getCouple());
            weddingRepository.save(wedding);
        }
        log.info("Wedding edited.");
    }

    public void editCouple(Long weddingId, String personOne, String personTwo) {
        Optional<Wedding> weddingOptional = weddingRepository.findById(weddingId);
        if(weddingOptional.isPresent()) {
            Wedding wedding = weddingOptional.get();
            wedding.getCouple().setPerson1(personOne);
            wedding.getCouple().setPerson2(personTwo);

            coupleRepository.save(wedding.getCouple());
        }
        log.info("Couple edited.");
    }

    public void editTask(TaskToDo taskToDoInfo) {
        Optional<TaskToDo> taskEdit = taskToDoRepository.findById(taskToDoInfo.getId());
        if(taskEdit.isPresent()) {
            TaskToDo taskToDo = taskEdit.get();
            taskToDo.setReminderDay(taskToDoInfo.getReminderDay());
            taskToDo.setDeadlineDay(taskToDoInfo.getDeadlineDay());
            taskToDo.setTimeOfTask(taskToDoInfo.getTimeOfTask());
            taskToDo.setPlannedCost(taskToDoInfo.getPlannedCost());
            taskToDoRepository.save(taskToDo);
        }
        log.info("Task edited.");
    }
//    public List<TaskToDo> getAll() {
//        return taskToDoRepository.findAll();
//    }
    public void removeTask(Long id) {
        taskToDoRepository.deleteById(id);
        log.info("Task removed.");
    }

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
//    public void removeCost(Long id) {
//        costRepository.deleteById(id);
//        log.info("Cost removed.");
//    }



//    public Double getAllCosts() {
//        costRepository.getAllCosts(getWeddingWithId());
//        return costRepository.;
//    }

//    public Double sumAllCosts (){
//        for (int i = 0; i <=taskToDoRepository.count() ; i++) {
//            long j = i;
//            Double CosttoAdd = ta
//            Double allCosts= 0.00;
//            allCosts
//                    taskToDoRepository.findById(j);
//        }
//
//        taskToDoRepository.findAll().stream().filter(cCost -> cCost.getPlannedCost());
//        return costRepository.findAll();
//
//        Optional<TypeOfTask> optionalTaskType = typeofTaskRepository.findAll()
//                .stream()
//                .filter(tTask -> tTask.getName().equalsIgnoreCase(ownTypeOfTask))
//                .findAny();
//    }

}