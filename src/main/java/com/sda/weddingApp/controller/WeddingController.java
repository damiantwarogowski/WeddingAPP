package com.sda.weddingApp.controller;

import com.sda.weddingApp.model.Wedding;
import com.sda.weddingApp.service.AccountService;
import com.sda.weddingApp.service.WeddingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/wedding")
@RequiredArgsConstructor
public class WeddingController {
    private final WeddingService weddingService;
    private final AccountService accountService;

    @GetMapping("/weddings")
    public String getAllWeddings(Model model) {
        List<Wedding> weddingList = weddingService.getAll();

        model.addAttribute("weddingsList", weddingList);
        return "wedding-list";
    }

    @GetMapping("/details/{identifier}")
    public String getAllWeddings(Model model, @PathVariable Long identifier) {
        Optional<Wedding> weddingOptional = weddingService.getWeddingWithId(identifier);
        if (weddingOptional.isPresent()) {
            model.addAttribute("wedding", weddingOptional.get());
            return "wedding-details";
        }
        return "redirect:/wedding/weddings";
    }

    @GetMapping("/remove/{id}")
    public String removeWedding(@PathVariable(name = "id") Long identificatory) {
        weddingService.removeWedding(identificatory);
        return "redirect:/wedding/weddings";
    }

    @GetMapping("/edit/{identif}")
    public String editWedding(Model model, @PathVariable(name="identif") Long id) {
        Optional<Wedding> weddingToEdit = weddingService.getWeddingWithId(id);
        if (weddingToEdit.isPresent()) {
            model.addAttribute("wedding_edit", weddingToEdit.get());
            log.info("Wedding to edit: " + weddingToEdit);
            return "wedding-edit";
        }
        return "redirect:/wedding";
    }

    @PostMapping("/edit/submit")
    public String submitSurvey(Wedding wedding) {
        weddingService.editWedding(wedding);
        return "redirect:/wedding/details/"+wedding.getId();
    }

    @GetMapping("/add")
    public String addWedding(Model model) {
        Wedding wedding = new Wedding();

        model.addAttribute("new_wedding", wedding);
        return "wedding-add";
    }

    @GetMapping("")
    public String getSurveyPage(Model model) {
        model.addAttribute("today", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return "wedding-edit";
    }

    @GetMapping("/couple/edit/{identif}")
    public String editCouple(Model model, @PathVariable(name="identif") Long id) {
        Optional<Wedding> weddingToEdit = weddingService.getWeddingWithId(id);
        if (weddingToEdit.isPresent()) {
            Wedding wedding = weddingToEdit.get();
            model.addAttribute("wedding_id", wedding.getId());
            model.addAttribute("person_one", wedding.getCouple().getPerson1());
            model.addAttribute("person_two", wedding.getCouple().getPerson2());
            return "couple-edit";
        }
        return "redirect:/wedding/details/"+id;
    }

    @PostMapping("/couple/edit/submit")
    public String editCouple(Long wedding_id, String person_one_name, String person_two_name) {
        weddingService.editCouple(wedding_id, person_one_name, person_two_name);
        return "redirect:/wedding/details/"+wedding_id;
    }
}
