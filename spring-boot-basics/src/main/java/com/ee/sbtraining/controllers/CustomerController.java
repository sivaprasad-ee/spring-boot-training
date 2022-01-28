package com.ee.sbtraining.controllers;

import com.ee.sbtraining.models.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @GetMapping("/api/customer")
    public Customer getCustomer() {
        return new Customer(1L, "Siva");
    }
}
