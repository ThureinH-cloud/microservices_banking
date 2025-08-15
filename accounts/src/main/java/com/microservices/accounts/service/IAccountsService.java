package com.microservices.accounts.service;


import com.microservices.accounts.dto.CustomerDto;

public interface IAccountsService {
    void create(CustomerDto customerDto);

    CustomerDto getAccountByMobileNumber(String mobileNumber);

    boolean deleteAccount(String mobileNumber);
}
