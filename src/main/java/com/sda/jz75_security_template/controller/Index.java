package com.sda.jz75_security_template.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class Index {

    @GetMapping
    public String getIndex(){
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }
    // MODELS:
    // - ADMIN REJESTRUJE UZYTKOWNIKOW
    // - UZYTKOWNICY REJESTRUJA SIE SAMI -

    // Plan:
    // - rejestracja uzytkownika
    // - listowanie uzytkonikow
    // - UI/użycie material/bootstrap


    @GetMapping("/authenticated")
    public String getAuthenticated(){
        return "authenticated";
    }
}
