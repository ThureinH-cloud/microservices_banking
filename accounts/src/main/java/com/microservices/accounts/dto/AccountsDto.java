package com.microservices.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {
    private Long customerId;
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}
