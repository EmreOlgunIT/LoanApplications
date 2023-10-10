package com.example.oppgave.loanapplication;

import com.example.oppgave.customer.Customer;
import com.example.oppgave.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/loanapplication")
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;
    private final CustomerService customerService;

    @Autowired
    public LoanApplicationController(LoanApplicationService loanApplicationService, CustomerService customerService) {
        this.loanApplicationService = loanApplicationService;
        this.customerService = customerService;
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<LoanApplication>>  getLoanApplications(){
        return new ResponseEntity<>(loanApplicationService.getLoanApplications(), HttpStatus.OK) ;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LoanApplication> registerLoanApplication(
            @RequestParam(name = "socialSecurityNumber") String socialSecurityNumber,
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName,
            @RequestParam(name = "loanAmount") Integer loanAmount,
            @RequestParam(name = "equityAmount") Integer equityAmount,
            @RequestParam(name = "salaryAmount") Integer salaryAmount
        ) {

        //Save customer info
        Customer customer = new Customer(socialSecurityNumber, firstName, lastName);
        Customer savedCustomer = customerService.addNewCustomer(customer);

        //Save loan application info
        LoanApplication loanApplication = new LoanApplication(loanAmount, equityAmount, salaryAmount, savedCustomer.getId());
        LoanApplication savedLoanApplication = loanApplicationService.addNewLoanApplication(loanApplication);

        return new ResponseEntity<>(savedLoanApplication, HttpStatus.OK);
    }

}