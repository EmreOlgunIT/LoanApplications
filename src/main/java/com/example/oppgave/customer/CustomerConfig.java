package com.example.oppgave.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerConfig {

    @Bean
    CommandLineRunner commandLineRunner1(CustomerRepository customerRepository) {
        
        return args -> {
            Customer emre = new Customer("18049712345", "Emre", "Olgun");
            Customer chet = new Customer("20044012345", "Chet", "Baker");
            customerRepository.saveAll(List.of(emre, chet));
        };
    }

}
