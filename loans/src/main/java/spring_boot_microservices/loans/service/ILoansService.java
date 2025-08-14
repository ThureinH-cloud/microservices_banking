package spring_boot_microservices.loans.service;

import spring_boot_microservices.loans.dto.LoansDto;

public interface ILoansService {
    void createLoan(String mobileNumber);

    LoansDto fetchLoan(String mobileNumber);

    boolean deleteLoan(String mobileNumber);
}
