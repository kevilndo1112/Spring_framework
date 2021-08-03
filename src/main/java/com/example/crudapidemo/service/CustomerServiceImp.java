package com.example.crudapidemo.service;

import com.example.crudapidemo.dao.CustomerRepository;
import com.example.crudapidemo.entity.Customer;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImp implements CustomerService{
    private CustomerRepository customerRepository;

    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Customer findById(int theId) {
        Optional<Customer> result = customerRepository.findById(theId);

        Customer theEmployee = null;

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
    public void save(Customer theEmployee) {
        customerRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        customerRepository.deleteById(theId);
    }

    @Override
    public List<Customer> searchBy(String theName) {
        List<Customer> results = null;

        if (theName != null && (theName.trim().length() > 0)) {
            results = customerRepository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(theName, theName);
        }
        else {
            results = findAll();
        }

        return results;
    }
}
