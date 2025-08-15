package com.spring_boot_microservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDto {
    private Long customerId;
    private String name;
    private String email;
    private String mobileNumber;
}
