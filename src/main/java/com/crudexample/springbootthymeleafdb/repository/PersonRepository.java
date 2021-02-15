package com.crudexample.springbootthymeleafdb.repository;

import com.crudexample.springbootthymeleafdb.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByUsername(String username);
}
