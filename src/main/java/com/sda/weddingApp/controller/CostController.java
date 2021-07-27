package com.sda.weddingApp.controller;

import com.sda.weddingApp.model.TaskCost;
import com.sda.weddingApp.model.TaskToDo;
import com.sda.weddingApp.model.Wedding;
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
    public String addNewCost(Model model, @PathVariable Long taskId, Double paymentAmount, LocalDate dateOfPayment,
                             LocalDate dateOfPaymentDeadline) {


        TaskCost taskCost = new TaskCost();
        Optional<TaskToDo> taskToDoOptional = typeOfTaskService.findTask(taskId);
        if (!taskToDoOptional.isPresent()) {
            return "redirect:/task/" + taskId;
        }
        model.addAttribute("new_cost", taskCost);
        model.addAttribute("taskId", taskId);
        model.addAttribute("task", taskToDoOptional.get());
//        model.addAttribute("all_types_of_costs", costService.findAll());
        model.addAttribute("paymentAmount", paymentAmount);
        model.addAttribute("dateOfPayment", dateOfPayment);
//        model.addAttribute("totalCost", totalCost);
        model.addAttribute("dateOfPaymentDeadline", dateOfPaymentDeadline);

        return "cost-of-task-add";
    }

    @PostMapping("/add")
    public String addCost(TaskCost taskCost, Long taskId, Double paymentAmount, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfPayment,
                         @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfPaymentDeadline) {
        log.info("Cost to save:" + taskCost.getId());
//        bailCostDeadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        totalCostDeadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        weddingService.addCostToTask(taskId, paymentAmount, dateOfPayment, dateOfPaymentDeadline);
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

    @GetMapping("/edit/{costId}")
    public String editCost(Model model, @PathVariable(name = "costId") Long id) {
        Optional<TaskCost> costToEdit = weddingService.findCostOfTask(id);
        if (costToEdit.isPresent()) {
            model.addAttribute("cost_edit", costToEdit.get());
            log.info("Cost to edit: " + costToEdit);
            return "cost-edit";
        }
        return "redirect:/tasks";
    }

    @PostMapping("/edit/submit")
    public String editCost(TaskCost taskCost) {
        weddingService.editCost(taskCost);
        Long taskId = taskCost.getTask().getId();
        return "redirect:/tasks/"+taskCost.getTask().getId();
//        Źle idzie ścieżka
//    +taskCost.getTask().getId()
    }

    @GetMapping("/remove/{id}")
    public String removeCost(@PathVariable(name = "id") Long identificatory) {
        Long taskId = weddingService.removeCost(identificatory);
        return "redirect:/tasks/" + taskId;
    }
}