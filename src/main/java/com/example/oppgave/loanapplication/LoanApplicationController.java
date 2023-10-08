package com.example.oppgave.loanapplication;

import com.example.oppgave.customer.Customer;
import com.example.oppgave.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<LoanApplication> getLoanApplications(){
        return loanApplicationService.getLoanApplications();
    }

    @PostMapping
    public void registerLoanApplication(
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
        loanApplicationService.addNewLoanApplication(loanApplication);
    }

}