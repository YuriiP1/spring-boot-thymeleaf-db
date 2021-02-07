package com.crudexample.springbootthymeleafdb.repository;

import com.crudexample.springbootthymeleafdb.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
