package com.microservices.accounts.service;


import com.microservices.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {
    CustomerDetailsDto getCustomerDetails(String mobileNumber);
}
