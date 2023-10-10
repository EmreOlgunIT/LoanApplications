package com.example.oppgave.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

class CustomerServiceTest {

    @Mock private CustomerRepository customerRepository;
    private CustomerService beingTested;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        beingTested = new CustomerService(customerRepository);
    }

    @AfterEach()
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCanGetCustomers() {
        beingTested.getCustomers();
        verify(customerRepository).findAll();
    }

    @Test
    void testCanAddNewCustomer() {
        Customer customer = new Customer ("1111111111", "Test", "Testing");

        beingTested.addNewCustomer(customer);

        ArgumentCaptor<Customer> customerArgumentCaptor = ArgumentCaptor.forClass(Customer.class);

        verify(customerRepository).save(customerArgumentCaptor.capture());

        Customer capturedCustomer = customerArgumentCaptor.getValue();

        assertThat(capturedCustomer).isEqualTo(customer);
    }
}