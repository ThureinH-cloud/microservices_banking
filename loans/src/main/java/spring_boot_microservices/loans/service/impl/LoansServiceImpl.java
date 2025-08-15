package spring_boot_microservices.loans.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring_boot_microservices.loans.constants.LoansConstants;
import spring_boot_microservices.loans.dto.LoansDto;
import spring_boot_microservices.loans.entity.Loans;
import spring_boot_microservices.loans.exception.LoanAlreadyExistsException;
import spring_boot_microservices.loans.exception.ResourceNotFoundException;
import spring_boot_microservices.loans.mapper.LoanMapper;
import spring_boot_microservices.loans.repository.LoansRepository;
import spring_boot_microservices.loans.service.ILoansService;

import java.beans.Transient;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class LoansServiceImpl implements ILoansService {
    private LoansRepository loansRepository;

    @Override
    @Transactional
    public void createLoan(String mobileNumber) {
        Optional<Loans> loans=loansRepository.findByMobileNumber(mobileNumber);
        if(loans.isPresent()){
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber "+mobileNumber);
        }
        loansRepository.save(createNewLoan(mobileNumber));
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        Loans loans=loansRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
        return LoanMapper.mapToLoansDto(loans,new LoansDto());
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loans loans=loansRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
        loansRepository.delete(loans);
        return true;
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

}
