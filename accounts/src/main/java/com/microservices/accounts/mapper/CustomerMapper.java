package com.spring_boot_microservices.mapper;

import com.spring_boot_microservices.dto.CustomerDto;
import com.spring_boot_microservices.entity.Customer;

public class CustomerMapper {
    public static CustomerDto mapToCustomerDto(Customer customer) {
        return new CustomerDto(
                customer.getCustomerId(),
                customer.getName(),
                customer.getEmail(),
                customer.getMobileNumber()
        );
    }
    public static Customer mapToCustomer(CustomerDto customerDto) {
        return new Customer(
                customerDto.getCustomerId(),
                customerDto.getName(),
                customerDto.getEmail(),
                customerDto.getMobileNumber()
        );
    }
}
