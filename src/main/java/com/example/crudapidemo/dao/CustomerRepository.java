package com.example.crudapidemo.dao;

import com.example.crudapidemo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public List<Customer> findAllByOrderByLastNameAsc();
    public List<Customer> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String name, String lName);

}
