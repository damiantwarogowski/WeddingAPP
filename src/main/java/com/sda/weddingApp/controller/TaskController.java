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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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
        model.addAttribute("plannedCost",taskToDo.getPlannedCost());
        model.addAttribute("all_types_of_tasks", typeOfTaskService.findAll());
        return "wedding-task-add";
    }

    // aby móc dodać rekord do bazy
    @PostMapping("/add")
    public String addTask(TaskToDo taskTodo, Long weddingId, Long type_of_task_id, String ownTypeOfTask, Double plannedCost) {
        log.info("Task to save:" + taskTodo);

        weddingService.addTaskToWedding(weddingId, type_of_task_id, taskTodo, ownTypeOfTask, plannedCost);
        return "redirect:/wedding/details/" + weddingId;
    }

    @GetMapping("/{idTask}")
    public String getAllTasks(Model model, @PathVariable Long idTask) {
        Optional<TaskToDo> taskOptional = typeOfTaskService.findTask(idTask);
        if (taskOptional.isPresent()) {
            model.addAttribute("task", taskOptional.get());
            return "task-details";
        }
        return "redirect:/wedding/weddings";
    }

    @GetMapping("/edit/{taskId}")
    public String editTask(Model model, @PathVariable(name = "taskId") Long id) {
        Optional<TaskToDo> taskToEdit = typeOfTaskService.findTask(id);
        if (taskToEdit.isPresent()) {
            model.addAttribute("task_edit", taskToEdit.get());
            log.info("Task to edit: " + taskToEdit);
            return "task-details-edit";
        }
        return "redirect:/tasks";
    }

    @PostMapping("/edit/submit")
    public String editTask(TaskToDo taskToDo) {
        weddingService.editTask(taskToDo);
        return "redirect:/tasks/"+taskToDo.getId();
    }

    @GetMapping("")
    public String getSurveyPage(Model model) {
        model.addAttribute("today", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return "task-details-edit";
    }

    @GetMapping("/remove/{id}")
    public String removeTask(@PathVariable(name = "id") Long identificatory, Wedding wedding) {
        weddingService.removeTask(identificatory);
        return "redirect:/wedding/details/" + wedding.getId();
    }
}