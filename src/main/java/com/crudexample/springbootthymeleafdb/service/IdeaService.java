package com.crudexample.springbootthymeleafdb.service;

import com.crudexample.springbootthymeleafdb.model.Idea;
import com.crudexample.springbootthymeleafdb.repository.IdeaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IdeaService {

    private final IdeaRepository ideaRepository;

    public IdeaService(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    public List<Idea> getAllIdeas() {
        return ideaRepository.findAll();
    }

    public void save(Idea idea) {
        ideaRepository.save(idea);
    }
}
