package ru.geekbrains.spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.mvc.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {

    PersonService personService;

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable int id, Model model) {
        model.addAttribute("person", personService.findById(id));
        return "person";
    }


}
