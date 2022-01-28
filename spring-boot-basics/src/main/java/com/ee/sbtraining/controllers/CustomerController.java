package com.ee.sbtraining.controllers;

import com.ee.sbtraining.models.Customer;
import com.ee.sbtraining.repo.ICustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    private final ICustomerRepository repo;

    public CustomerController(/*@Qualifier("mysql")*/ ICustomerRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/api/customer")
    public Customer getCustomer() {
        return new Customer(1L, "Siva");
    }

    @GetMapping("/api/customer/names")
    public List<String> getCustomerNames() {
        return repo.getCustomerNames();
    }

}
