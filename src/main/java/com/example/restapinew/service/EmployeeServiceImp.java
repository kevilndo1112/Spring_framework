package com.example.restapinew.service;

import com.example.restapinew.dao.EmployeeRepository;
import com.example.restapinew.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImp implements EmployeeService{


    private EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImp(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = repository.findAll();
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result =repository.findById(theId);

        Employee theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        repository.save(theEmployee);
        return theEmployee;
    }

    @Override
    public void deleteById(int theId) {
        repository.deleteById(theId);
    }

    @Override
    public List<Employee> searchBy(String theName) {
        List<Employee> results = null;

        if (theName != null && (theName.trim().length() > 0)) {
            results = repository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(theName,theName);
        }
        else {
            results = findAll();
        }

        return results;
    }

    @Override
    public Employee replaceEmployee(Employee newEmployee, Integer id) {

        return  repository.findById(id)
                .map(employee -> {
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setLastName(newEmployee.getLastName());
                    employee.setEmail(newEmployee.getEmail());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

}
