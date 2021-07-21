package com.sda.weddingApp.controller;

import com.sda.weddingApp.model.Account;
import com.sda.weddingApp.model.Wedding;
import com.sda.weddingApp.service.AccountService;
import com.sda.weddingApp.service.WeddingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

//    @GetMapping("/remove")
//    public String removeWedding(Long weddingId, Long weddingId){
//        weddingService.removeWedding(weddingId);
//
//        // po tym jak usuniemy studenta wracamy na stronę z listą (lub wrócimy do miejsca które nas redirectowało)
//        return "redirect:/wedding/details?weddingId="+ weddingId;

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

    @GetMapping("/add")
    public String addWedding(Model model) {
        Wedding wedding = new Wedding();

        model.addAttribute("new_wedding", wedding);
        return "wedding-add";
    }

    @PostMapping("/edit/submit")
    public String submitSurvey(Wedding wedding) {
        weddingService.editWedding(wedding);
        return "redirect:/";
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
            model.addAttribute("wedding", wedding);
            return "couple-edit";
        }
        return "redirect:/wedding";
    }

    @PostMapping("/couple/edit/submit")
    public String editCouple(Wedding wedding, Long couple_person_one, Long couple_person_two) {
        weddingService.editCouple(wedding, couple_person_one, couple_person_two);
        return "redirect:/";
    }
}
