package com.sda.weddingApp.controller;

import com.sda.weddingApp.model.dto.SurveyAnswers;
import com.sda.weddingApp.service.AccountService;
import com.sda.weddingApp.service.WeddingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Controller
@RequestMapping("/survey")
@RequiredArgsConstructor
public class SurveyController {
    private final WeddingService weddingService;
    private final AccountService accountService;

    @GetMapping("")
    public String getSurveyPage(Model model) {
        model.addAttribute("today", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return "survey-add";
    }

    @PostMapping("/submit")
    public String submitSurvey(SurveyAnswers answers, Principal principal) {
        weddingService.createWedding(answers, accountService.extractIdFromPrincipal(principal));
        return "redirect:/wedding/weddings";
    }
}
