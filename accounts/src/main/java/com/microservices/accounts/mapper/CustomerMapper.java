package com.microservices.accounts.mapper;


import com.microservices.accounts.dto.CustomerDetailsDto;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.entity.Customer;

public class CustomerMapper {
    public static CustomerDto mapToCustomerDto(Customer customer,CustomerDto customerDto) {
        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setEmail(customer.getEmail());
        customerDto.setName(customer.getName());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }
    public static CustomerDetailsDto mapToCustomerDetailsDto(Customer customer,CustomerDetailsDto customerDetailsDto) {
        customerDetailsDto.setName(customer.getName());
        customerDetailsDto.setMobileNumber(customer.getMobileNumber());
        customerDetailsDto.setEmail(customer.getEmail());
        return customerDetailsDto;
    }
    public static Customer mapToCustomer(CustomerDto customerDto,Customer customer) {
        customer.setCustomerId(customerDto.getCustomerId());
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
