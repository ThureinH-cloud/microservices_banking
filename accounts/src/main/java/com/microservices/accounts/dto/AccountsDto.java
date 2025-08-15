package com.spring_boot_microservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountsDto {
    private Long customerId;
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}
