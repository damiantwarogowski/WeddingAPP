package com.sda.weddingApp.service;

import com.sda.weddingApp.model.Account;
import com.sda.weddingApp.model.TaskToDo;
import com.sda.weddingApp.model.TypeOfTask;
import com.sda.weddingApp.model.Wedding;
import com.sda.weddingApp.model.dto.SurveyAnswers;
import com.sda.weddingApp.repository.AccountRepository;
import com.sda.weddingApp.repository.TaskToDoRepository;
import com.sda.weddingApp.repository.TypeofTaskRepository;
import com.sda.weddingApp.repository.WeddingFormRepository;
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

    public void createWedding(SurveyAnswers answers, Long accountId) {
        Account account = accountRepository.getById(accountId);

        Wedding wedding = Wedding.builder()
                .dateOfWedding(answers.getWeddingDate())
                .timeOfWedding(answers.getWeddingTime())
                .owner(account)
                .build();
        wedding = weddingRepository.save(wedding);
        log.info("Wedding zapisany.");

        if (answers.isTaskBand()) {
            addTaskToWedding(wedding, TASK_BAND, answers.getWeddingDate());
        }
        if (answers.isTaskDJ()) {
            addTaskToWedding(wedding, TASK_DJ, answers.getWeddingDate());
        }
        if (answers.isTaskVenue()) {
            addTaskToWedding(wedding, TASK_VENUE, answers.getWeddingDate());
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

    public List<Wedding> getAll() {
        return weddingRepository.findAll();
    }

    public void removeWedding(Long id) {
        weddingRepository.deleteById(id);
    }


    public Optional<Wedding> getWeddingWithId(Long id) {
        return weddingRepository.findById(id);
    }
//    public boolean removeWeddingWithId(Long id) {
//        Optional<Wedding> weddingOptional = getWeddingWithId(id);
//        if(weddingOptional.isPresent()){
//            removeWeddingWithId(id);
//            return true;
//        }
//        return false;
//    }
//
//    public Optional<WeddingDto> update(Long id, WeddingDto weddingDto) {
//        Optional<Wedding> weddingOptional = getWeddingWithId(id);
//        if(weddingOptional.isPresent()){
//            Wedding wedding = weddingOptional.get();
//
//            weddingMapper.update(weddingDto, wedding);
//            return Optional.of(weddingMapper.getDtoFromWedding(weddingRepository.save(wedding)));
//        }
//        return Optional.empty();
//    }
}
