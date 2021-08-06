package com.example.restapinew.rest;


import com.example.restapinew.dao.EmployeeRepository;
import com.example.restapinew.entity.Employee;
import com.example.restapinew.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService repository;

    public EmployeeRestController(EmployeeService repository) {
        this.repository = repository;
    }


    @GetMapping("/emp")
    List<Employee> all() {
        return repository.findAll();
    }

    @PostMapping("/emp")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    @GetMapping("/emp/{id}")
    Employee find(@PathVariable Integer id) {
        return repository.findById(id);
    }

    @DeleteMapping("/emp/{id}")
    void deleteEmployee(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @PutMapping("/emp/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Integer id) {

        return repository.replaceEmployee(newEmployee, id);
    }
}