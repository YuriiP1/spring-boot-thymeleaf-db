package com.crudexample.springbootthymeleafdb.service;

import com.crudexample.springbootthymeleafdb.model.Person;
import com.crudexample.springbootthymeleafdb.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService implements UserDetailsService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public boolean existsById(Long id) {
        return personRepository.existsById(id);
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person save(Person person){
//        if(person.getId() != null && existsById(person.getId())) {
//            throw new Exception("Person with " + person.getId() + " already exists.");
//        }
        return personRepository.save(person);
    }

    public Person findById(Long id) {
        Optional<Person> optional = personRepository.findById(id);
        Person person = null;
        if(optional.isPresent()) {
            person = optional.get();
        } else {
            throw new RuntimeException("Person not found by this id: " + id);
        }
        return person;
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    public Person findPersonByUsername(String username) {
        return personRepository.findByUsername(username);
    }

    public Page<Person> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.personRepository.findAll(pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.findByUsername(username);
    }
}
