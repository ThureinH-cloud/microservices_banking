package com.spring_boot_microservices.service.impl;

import com.spring_boot_microservices.constant.AccountsConstant;
import com.spring_boot_microservices.dto.AccountsDto;
import com.spring_boot_microservices.dto.CustomerDto;
import com.spring_boot_microservices.entity.Accounts;
import com.spring_boot_microservices.entity.Customer;
import com.spring_boot_microservices.exception.CustomerAlreadyExistsException;
import com.spring_boot_microservices.mapper.CustomerMapper;
import com.spring_boot_microservices.repository.AccountsRepository;
import com.spring_boot_microservices.repository.CustomerRepository;
import com.spring_boot_microservices.service.IAccountsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;

    private CustomerRepository customerRepository;
    @Override
    public void create(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto);
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    +customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("system");
        Customer saved_customer=customerRepository.save(customer);
        accountsRepository.save(createNewAccount(saved_customer));
    }

    @Override
    public CustomerDto getAccountByMobileNumber(String mobileNumber) {
        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "+mobileNumber));
        return CustomerMapper.mapToCustomerDto(customer);
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstant.SAVINGS);
        newAccount.setBranchAddress(AccountsConstant.ADDRESS);
        return newAccount;
    }

}
