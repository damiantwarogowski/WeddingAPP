package com.sda.weddingApp.controller;

import com.sda.weddingApp.model.TaskToDo;
import com.sda.weddingApp.service.AccountService;
import com.sda.weddingApp.service.TypeOfTaskService;
import com.sda.weddingApp.service.WeddingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final WeddingService weddingService;
    private final AccountService accountService;
    private final TypeOfTaskService typeOfTaskService;

    // aby móc wyświetlić formularz
    @GetMapping("/add/{weddingId}")
    public String addNewTask(Model model, @PathVariable Long weddingId) {
        TaskToDo taskToDo = new TaskToDo();

        // dla użytkownika wysyłam obiekt studenta który ma być wypełniony w formularzu
        model.addAttribute("new_task", taskToDo);
        model.addAttribute("weddingId", weddingId);
        model.addAttribute("all_types_of_tasks", typeOfTaskService.findAll());
        return "wedding-task-add";
    }

    // aby móc dodać rekord do bazy
    @PostMapping("/add")
    public String addTask(TaskToDo taskTodo, Long weddingId, Long type_of_task_id, String ownTypeOfTask) {
        log.info("Task to save:" + taskTodo);
        weddingService.addTaskToWedding(weddingId, type_of_task_id, taskTodo, ownTypeOfTask);
        return "redirect:/wedding/details/"+weddingId;
    }
}