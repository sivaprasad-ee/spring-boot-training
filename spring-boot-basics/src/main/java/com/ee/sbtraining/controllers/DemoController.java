package com.ee.sbtraining.controllers;

import com.ee.sbtraining.config.AlertsProperties;
import com.ee.sbtraining.config.SupportProperties;
import com.ee.sbtraining.models.Customer;
import com.ee.sbtraining.repo.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
    @Autowired
    @Qualifier("mongo")
    private ICustomerRepository repo;

    @Value("${support.email}")
    private String supportEmail;

    @Value("${support.phone}")
    private String supportPhone;

    @Autowired
    SupportProperties supportProperties;

    @Autowired
    AlertsProperties alertsProperties;

    @GetMapping("/")
    public String index(Model model) {

        System.out.println("from Value supportEmail = "+ supportEmail);
        System.out.println("from Value supportPhone = "+ supportPhone);

        System.out.println("from Obj support= "+ supportProperties);
        System.out.println("from Obj alerts= "+ alertsProperties);

        model.addAttribute("username", "Siva");
        return "index";
    }

    @GetMapping("/test")
    @ResponseBody
    public Customer getCustomer() {
        return new Customer(1L, "Siva");
    }
}
