package com.example.crudapidemo.rest;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Integer id) {
        super("Could not find employee " + id);
    }
}