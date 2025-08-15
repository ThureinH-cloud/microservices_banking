package com.spring_boot_microservices.service;

import com.spring_boot_microservices.dto.CustomerDto;

public interface IAccountsService {
    void create(CustomerDto customerDto);

    CustomerDto getAccountByMobileNumber(String mobileNumber);
}
