package com.sda.weddingApp.controller;

import com.sda.weddingApp.model.TaskCost;
import com.sda.weddingApp.service.CostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        TaskCost taskCost = new TaskCost();

        model.addAttribute("new_cost", taskCost);
        model.addAttribute("taskId", taskId);
        return "task-cost-add";
    }

    @PostMapping("/add")
    public String addCost(TaskCost taskCost, Long taskId) {
        log.info("Cost to save:" + taskCost);
        costService.addCostToTask(taskCost, taskId);
        return "redirect:/tasks/"+taskId;
    }

    @GetMapping("/{idCost}")
    public String getAllCosts(Model model, @PathVariable Long idCost) {
        Optional<TaskCost> costOptional = costService.findCostOfTask(idCost);
        if (costOptional.isPresent()) {
            model.addAttribute("cost", costOptional.get());
            return "cost-details";
        }
        return "redirect:/";
    }
}