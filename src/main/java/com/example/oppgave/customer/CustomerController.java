package com.example.oppgave.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @CrossOrigin
    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping(path = "{id}")
    public Customer getCustomerById(@PathVariable("id")  Integer id){
        return customerService.getCustomerById(id);
    }

}
