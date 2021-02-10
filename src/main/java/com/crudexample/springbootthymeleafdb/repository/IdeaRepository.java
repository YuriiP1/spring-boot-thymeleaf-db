package com.crudexample.springbootthymeleafdb.repository;

import com.crudexample.springbootthymeleafdb.model.Idea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IdeaRepository extends JpaRepository<Idea,Long> {
}
