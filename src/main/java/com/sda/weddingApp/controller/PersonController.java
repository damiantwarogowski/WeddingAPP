package com.sda.weddingApp.controller;

import com.sda.weddingApp.model.Person;
import com.sda.weddingApp.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping()
    public String getAllPersons(Model model){
        List<Person> personList = personService.getAll();

        model.addAttribute("lista_studencikow", personList);
        return "person-list";
    }

    @GetMapping("/details")
    public String getAllPersons(Model model, Long personId){
        Optional<Person> personDetails = personService.getPersonWithId(personId);
        if(personDetails.isPresent()) {
            model.addAttribute("person-details", personDetails.get());

            return "person-details";
        }

        return "redirect:/person";
    }

    // aby móc usunąć studenta
    @GetMapping("/remove")
    public String removePerson(@RequestParam(name = "id") Long identyfikatorek){
        personService.removePerson(identyfikatorek);

        // po tym jak usuniemy studenta wracamy na stronę z listą (lub wrócimy do miejsca które nas redirectowało)
        return "redirect:/person";
    }

    // aby móc edytowac studenta
    @GetMapping("/edit")
    public String editPerson(Model model, @RequestParam(name = "id_person_to_edit") Long id){
        Optional<Person> personToEdit = personService.getPersonWithId(id);
        if(personToEdit.isPresent()){
            model.addAttribute("nowy_person", personToEdit.get());

            log.info("Person to edit: " + personToEdit);
            return "person-add";
        }
        // jeśli nie udało się znaleźć studenta, to wracamy na listę studentów
        return "redirect:/person";
    }

    // aby móc wyświetlić formularz
    @GetMapping("/add")
    public String addPersonForm(Model model){
        Person person = new Person();

        // dla użytkownika wysyłam obiekt studenta który ma być wypełniony w formularzu
        model.addAttribute("new_person", person);
        return "person-add";
    }

    // aby móc dodać rekord do bazy
    @PostMapping("/add")
    public String addPerson(Person person){
        log.info("Student do zapisu: " + person);
        personService.addPerson(person);
        return "redirect:/person";
    }
}