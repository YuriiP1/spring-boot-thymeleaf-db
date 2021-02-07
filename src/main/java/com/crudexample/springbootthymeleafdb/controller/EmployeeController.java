package com.crudexample.springbootthymeleafdb.controller;

import com.crudexample.springbootthymeleafdb.model.Employee;
import com.crudexample.springbootthymeleafdb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
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

    @GetMapping("show-new-employee")
    public String showNewEmployee(Model model) {
        model.addAttribute("newEmployee", new Employee());
        return "new_employee";
    }

    @PostMapping("save-employee")
    public String saveEmployee(@ModelAttribute("newEmployee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/home";
    }

    @GetMapping("show-new-employee/{id}")
    public String showEmployeeById(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.findById(id));
        return "update_employee";
    }

    @GetMapping("delete-employee/{id}")
    public String deleteById(@PathVariable Long id){
        employeeService.deleteById(id);
        return "redirect:/home";
    }

    @GetMapping("page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDirection") String sortDirection,
                                Model model) {
        int pageSize = 5;

        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize,sortField,sortDirection);

        List<Employee> employeeList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());

        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDirection",sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc".toUpperCase() : "asc".toUpperCase());

        model.addAttribute("employees",employeeList);

        return "home";
    }
}
