package com.sda.weddingApp.controller;

import com.sda.weddingApp.service.WeddingFormService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/survey")
@RequiredArgsConstructor
public class WeddingFormController {
    private final WeddingFormService weddingFormService;

    @GetMapping("/add")
    public String addWeddingForm(Model model, Long wedding_to_which_we_want_to_add_form){
//        model.addAttribute("newWeddingForm", new WeddingForm()); - nie powinno być takiej klasy?
        model.addAttribute("hiddenStudentId", wedding_to_which_we_want_to_add_form);
        return "survey-add";
    }
}
