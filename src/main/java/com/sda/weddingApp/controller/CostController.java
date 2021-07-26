package com.sda.weddingApp.controller;

import com.sda.weddingApp.model.TaskCost;
import com.sda.weddingApp.model.TaskToDo;
import com.sda.weddingApp.service.TypeOfTaskService;
import com.sda.weddingApp.service.WeddingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

//import com.sda.weddingApp.service.CostService;

@Slf4j
@Controller
@RequestMapping("/cost")
@RequiredArgsConstructor
public class CostController {
    private final WeddingService weddingService;
    private final TypeOfTaskService typeOfTaskService;

    @GetMapping("")
    public String getCostPage(Model model) {
        model.addAttribute("today", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return "cost-of-task-add";
    }

    // aby móc wyświetlić formularz
    @GetMapping("/add/{taskId}")
    public String addNewCost(Model model, @PathVariable Long taskId, Double bailCost, LocalDate bailCostDeadline, Double totalCost, LocalDate totalCostDeadline) {


        TaskCost taskCost = new TaskCost();
        Optional<TaskToDo> taskToDoOptional = typeOfTaskService.findTask(taskId);
        if (!taskToDoOptional.isPresent()) {
            return "redirect:/task/" + taskId;
        }
        model.addAttribute("new_cost", taskCost);
        model.addAttribute("taskId", taskId);
        model.addAttribute("task", taskToDoOptional.get());
//        model.addAttribute("all_types_of_costs", costService.findAll());
        model.addAttribute("bailCost", bailCost);
        model.addAttribute("bailCostDeadline", bailCostDeadline);
        model.addAttribute("totalCost", totalCost);
        model.addAttribute("totalCostDeadline", totalCostDeadline);

        return "cost-of-task-add";
    }

    @PostMapping("/add")
    public String addCost(TaskCost taskCost, Long taskId, Double bailCost, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate bailCostDeadline, Double totalCost, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate totalCostDeadline) {
        log.info("Cost to save:" + taskCost.getId());
//        bailCostDeadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        totalCostDeadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        weddingService.addCostToTask(taskId, bailCost, bailCostDeadline, totalCost, totalCostDeadline);
        return "redirect:/tasks/" + taskId;
    }

    @GetMapping("/{idCost}")
    public String getAllCosts(Model model, @PathVariable Long idCost) {
        Optional<TaskCost> costOptional = weddingService.findCostOfTask(idCost);
        if (costOptional.isPresent()) {
            model.addAttribute("cost", costOptional.get());
            return "cost-details";
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{idCostEdit}")
    public String editCost(Model model, @PathVariable(name = "idCostEdit") Long id) {
        Optional<TaskCost> costToEdit = weddingService.findCostOfTask(id);
        if (costToEdit.isPresent()) {
            model.addAttribute("cost_edit", costToEdit.get());
            log.info("Cost to edit: " + costToEdit);
            return "cost-edit";
        }
        return "redirect:/tasks";
    }

//    @GetMapping("/remove/{id}")
//    public String removeCost(@PathVariable(name = "id") Long identificatory, Wedding wedding) {
//        costService.removeCost(identificatory);
//        return "redirect:/tasks/" + wedding.getId();
//        //ID WEDDING CZY ID TASK?
//    }
}