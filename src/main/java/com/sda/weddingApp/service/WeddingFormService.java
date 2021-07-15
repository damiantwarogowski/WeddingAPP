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
import java.util.Optional;

import static com.sda.weddingApp.configuration.TaskNames.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeddingFormService {
    private final WeddingFormRepository weddingFormRepository;
    private final AccountRepository accountRepository;
    private final TypeofTaskRepository typeofTaskRepository;
    private final TaskToDoRepository taskToDoRepository;

    public void createWedding(SurveyAnswers answers, Long accountId) {
        Account account = accountRepository.getById(accountId);

        Wedding wedding = Wedding.builder()
                .dateOfWedding(answers.getWeddingDate())
                .timeOfWedding(answers.getWeddingTime())
                .timeOfWeddingParty(answers.getWeddingPartyTime())
                .owner(account)
                .build();
        wedding = weddingFormRepository.save(wedding);
        log.info("Wedding zapisany.");

        if(answers.isTaskBand()) {
            addTaskToWedding(wedding, TASK_BAND, answers.getWeddingDate());
        }
        if(answers.isTaskDJ()) {
            addTaskToWedding(wedding, TASK_DJ, answers.getWeddingDate());
        }
        if(answers.isTaskVenue()) {
            addTaskToWedding(wedding, TASK_VENUE, answers.getWeddingDate());
        }
        if(answers.isTaskAuto()) {
            addTaskToWedding(wedding, TASK_AUTO, answers.getWeddingDate());
        }
        if(answers.isTaskAlcohol()) {
            addTaskToWedding(wedding, TASK_ALCOHOL, answers.getWeddingDate());
        }
        if(answers.isTaskInvitations()) {
            addTaskToWedding(wedding, TASK_INVITATIONS, answers.getWeddingDate());
        }
        if(answers.isTaskPhotographer()) {
            addTaskToWedding(wedding, TASK_PHOTOGRAPHER, answers.getWeddingDate());
        }
        if(answers.isTaskRingsTogether()) {
            addTaskToWedding(wedding, TASK_RINGS, answers.getWeddingDate());
        }

        if(answers.isTaskRingsSeparately()){
            addTaskToWedding(wedding, TASK_RINGS1, answers.getWeddingDate());
            addTaskToWedding(wedding, TASK_RINGS2, answers.getWeddingDate());
        }

        if(answers.isTaskOutfit1()) {
            addTaskToWedding(wedding, TASK_OUTFIT1, answers.getWeddingDate());
        }
        if(answers.isTaskHairdresser1()) {
            addTaskToWedding(wedding, TASK_HAIRDRESSER1, answers.getWeddingDate());
        }
        if(answers.isTaskBarber1()) {
            addTaskToWedding(wedding, TASK_BARBER1, answers.getWeddingDate());
        }
        if(answers.isTaskMakeupArtist1()) {
            addTaskToWedding(wedding, TASK_MAKEUP_ARTIST1, answers.getWeddingDate());
        }
        if(answers.isTaskManiPediCure1()) {
            addTaskToWedding(wedding, TASK_MANI_PEDI_CURE1, answers.getWeddingDate());
        }

        if(answers.isTaskOutfit2()) {
            addTaskToWedding(wedding, TASK_OUTFIT2, answers.getWeddingDate());
        }
        if(answers.isTaskHairdresser2()) {
            addTaskToWedding(wedding, TASK_HAIRDRESSER2, answers.getWeddingDate());
        }
        if(answers.isTaskBarber2()) {
            addTaskToWedding(wedding, TASK_BARBER2, answers.getWeddingDate());
        }
        if(answers.isTaskMakeupArtist2()) {
            addTaskToWedding(wedding, TASK_MAKEUP_ARTIST2, answers.getWeddingDate());
        }
        if(answers.isTaskManiPediCure2()) {
            addTaskToWedding(wedding, TASK_MANI_PEDI_CURE2, answers.getWeddingDate());
        }

    }

    private void addTaskToWedding(Wedding wedding, String name, LocalDate date) {
        Optional<TypeOfTask> optionalTaskType = typeofTaskRepository.findByName(name);
        if(optionalTaskType.isPresent()){
            TypeOfTask taskType = optionalTaskType.get();

            TaskToDo taskToDo = TaskToDo.builder()
                    .typeOfTask(taskType)
                    .deadlineDay(date)
                    .wedding(wedding)
                    .build();

            taskToDoRepository.save(taskToDo);
        }
    }
}
