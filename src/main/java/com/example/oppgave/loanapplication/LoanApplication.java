package com.example.oppgave.loanapplication;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor //Generates a constructor with all arguments
@NoArgsConstructor //Generates a constructor with no arguments
@Data //Generates getters and setters
@Entity
@Table
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Integer loanAmount;
    private Integer equityAmount;
    private Integer salaryAmount;
    private Integer customerId;

    public LoanApplication(Integer loanAmount, Integer equityAmount, Integer salaryAmount, Integer customerId) {
        this.loanAmount = loanAmount;
        this.equityAmount = equityAmount;
        this.salaryAmount = salaryAmount;
        this.customerId = customerId;
    }

}
