package com.sda.jz75_security_template.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/admin")
// ######################################################################################
//@Secured("ROLE_ADMIN")                    // musi mieć koniecznie tą rolę
@PreAuthorize(value = "hasRole('ADMIN')")   // to jest równoważne linii wyżej
// ######################################################################################
//@PreAuthorize(value = "hasAnyRole('ADMIN', 'SUPERVISOR')") // musi mieć którąkolwiek z ról, są dwie akceptowalne

@RequiredArgsConstructor
public class AdminController {

    @GetMapping
    public String getIndex(){
        return "admin-index";
    }

}
