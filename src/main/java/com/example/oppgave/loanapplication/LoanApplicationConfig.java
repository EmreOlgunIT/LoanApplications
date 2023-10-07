package com.example.oppgave.loanapplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LoanApplicationConfig {

    @Bean
    CommandLineRunner commandLineRunner2(LoanApplicationRepository loanApplicationRepository) {

        return args -> {
            LoanApplication application1 = new LoanApplication(3000000, 500000, 900000, 1);
            LoanApplication application2 = new LoanApplication(5000000, 1000000, 1200000, 2);
            loanApplicationRepository.saveAll(List.of(application1, application2));
        };

    }

}
