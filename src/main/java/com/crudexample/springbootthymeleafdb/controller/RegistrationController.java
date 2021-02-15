package com.crudexample.springbootthymeleafdb.controller;

import com.crudexample.springbootthymeleafdb.model.Person;
import com.crudexample.springbootthymeleafdb.model.Role;
import com.crudexample.springbootthymeleafdb.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class RegistrationController {

    private PersonService personService;

    @Autowired
    public RegistrationController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("person",new Person());
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewPerson(@ModelAttribute("person") @Valid Person person,
                            BindingResult result,
                            Model model) {
        Person personDb = personService.findPersonByUsername(person.getUsername());

        if(personDb != null) {
            model.addAttribute("message","User exists!");
            return "registration";
        }
        if(result.hasErrors()) {
            return "registration";
        }
        person.setActive(true);
        person.setRoles(Collections.singleton(Role.USER));
        try {
            personService.save(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/login";
    }
}
