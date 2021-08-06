package com.example.restapinew.service;

import com.example.restapinew.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EmployeeService {
    public List<Employee> findAll();

    public Employee findById(int theId);

    public Employee save(Employee theEmployee);

    public void deleteById(int theId);

    public List<Employee> searchBy(String theName);
    public Employee replaceEmployee( Employee newEmployee,  Integer id);

}
