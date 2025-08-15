package com.spring_boot_microservices.mapper;

import com.spring_boot_microservices.dto.AccountsDto;
import com.spring_boot_microservices.entity.Accounts;

public class AccountMapper {
    public static AccountsDto mapToAccountsDto(Accounts accounts) {
        return new AccountsDto(
                accounts.getCustomerId(),
                accounts.getAccountNumber(),
                accounts.getAccountType(),
                accounts.getBranchAddress()
        );
    };
    public static Accounts mapToAccounts(AccountsDto accountsDto) {
        return new Accounts(
                accountsDto.getAccountNumber(),
                accountsDto.getCustomerId(),
                accountsDto.getAccountType(),
                accountsDto.getBranchAddress()
        );
    }
}
