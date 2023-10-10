package com.example.oppgave.loanapplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

class LoanApplicationServiceTest {

    @Mock private LoanApplicationRepository loanApplicationRepository;
    private LoanApplicationService beingTested;
    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        beingTested = new LoanApplicationService(loanApplicationRepository);
    }

    @AfterEach()
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCanGetLoanApplications() {
        beingTested.getLoanApplications();
        verify(loanApplicationRepository).findAll();
    }

    @Test
    void testCanAddNewLoanApplication() {
        LoanApplication loanApplication = new LoanApplication (10000, 10000, 10000, 1);

        beingTested.addNewLoanApplication(loanApplication);

        ArgumentCaptor<LoanApplication> loanApplicationArgumentCaptor = ArgumentCaptor.forClass(LoanApplication.class);

        verify(loanApplicationRepository).save(loanApplicationArgumentCaptor.capture());

        LoanApplication capturedLoanApplication = loanApplicationArgumentCaptor.getValue();

        assertThat(capturedLoanApplication).isEqualTo(loanApplication);
    }
}