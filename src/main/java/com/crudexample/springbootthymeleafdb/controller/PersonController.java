package com.crudexample.springbootthymeleafdb.controller;

import com.crudexample.springbootthymeleafdb.model.Person;
import com.crudexample.springbootthymeleafdb.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

//    @GetMapping("home")
//    public String viewHomePage(Model model) {
//        model.addAttribute("employees", employeeService.getAllEmployees());
//        return "home";
//    }

    @GetMapping("home")
    public String viewHomePage(Model model) {
        findPaginated(1, "firstName","asc", model);
        return "home";
    }

    @GetMapping("show-new-person")
    public String showNewPerson(@Valid Model model) {
        model.addAttribute("newPerson", new Person());
        return "new_person";
    }

    @PostMapping("save-person")
    public String savePerson(@Valid @ModelAttribute("newPerson") Person person) throws Exception {
        personService.save(person);
        return "redirect:/home";
    }

    @GetMapping("show-new-person/{id}")
    public String showPersonById(@PathVariable Long id, Model model) {
        model.addAttribute("person", personService.findById(id));
        return "update_person";
    }

    @GetMapping("delete-person/{id}")
    public String deleteById(@PathVariable Long id){
        personService.deleteById(id);
        return "redirect:/home";
    }

    @GetMapping("page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDirection") String sortDirection,
                                Model model) {
        int pageSize = 5;

        Page<Person> page = personService.findPaginated(pageNo, pageSize,sortField,sortDirection);

        List<Person> personList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());

        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc".toUpperCase() : "asc".toUpperCase());

        model.addAttribute("persons", personList);

        return "home";
    }
}
