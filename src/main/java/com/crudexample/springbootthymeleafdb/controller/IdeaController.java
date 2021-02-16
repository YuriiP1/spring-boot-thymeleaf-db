package com.crudexample.springbootthymeleafdb.controller;

import com.crudexample.springbootthymeleafdb.model.Idea;
import com.crudexample.springbootthymeleafdb.service.IdeaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class IdeaController {

    private final IdeaService ideaService;

    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @GetMapping("/idea")
    public String addIdea(@Valid Model model) {
        model.addAttribute("newIdea", new Idea());
        model.addAttribute("ideas",ideaService.getAllIdeas());

        return "idea";
    }

    @PostMapping("/idea")
    public String savePerson(@ModelAttribute("newIdea") @Valid Idea idea){
        ideaService.save(idea);
        return "redirect:/idea";
    }
}
