package com.sda.weddingApp.controller;

import com.sda.weddingApp.model.TaskCost;
import com.sda.weddingApp.service.CostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/cost")
@RequiredArgsConstructor
public class CostController {
    private final CostService costService;

    // aby móc wyświetlić formularz
    @GetMapping("/add/{taskId}")
    public String addNewCost(Model model, @PathVariable Long taskId) {
        //,double bailCost, LocalDate bailCostDeadline, double totalCost, LocalDate totalCostDeadline
        TaskCost taskCost = new TaskCost();

        model.addAttribute("new_cost", taskCost);
        model.addAttribute("taskId", taskId);
//        model.addAttribute("all_types_of_costs", costService.findAll());
//        model.addAttribute("bailCost",bailCost);
//        model.addAttribute("bailCostDeadline",bailCostDeadline);
//        model.addAttribute("totalCost",totalCost);
//        model.addAttribute("totalCostDeadline",totalCostDeadline);

        return "cost-of-task-add";
    }

//    @PostMapping("/add")
//    public String addCost(TaskCost taskCost, Long costId, double bailCost, LocalDate bailCostDeadline, double totalCost, LocalDate totalCostDeadline) {
//        log.info("Cost to save:" + taskCost);
//        costService.addCostToTask(taskCost, costId,bailCost,bailCostDeadline,totalCost, totalCostDeadline);
//        return "redirect:/tasks/"+costId;
//    }

    @GetMapping("/{idCost}")
    public String getAllCosts(Model model, @PathVariable Long idCost) {
        Optional<TaskCost> costOptional = costService.findCostOfTask(idCost);
        if (costOptional.isPresent()) {
            model.addAttribute("cost", costOptional.get());
            return "cost-details";
        }
        return "redirect:/";
    }
    @GetMapping("/edit/{idCostEdit}")
    public String editCost(Model model, @PathVariable(name="idCostEdit") Long id) {
        Optional<TaskCost> costToEdit = costService.findCostOfTask(id);
        if (costToEdit.isPresent()) {
            model.addAttribute("cost_edit", costToEdit.get());
            log.info("Cost to edit: " + costToEdit);
            return "cost-edit";
        }
        return "redirect:/tasks";
    }
}