package com.spring_boot_microservices.controller;

import com.spring_boot_microservices.constant.AccountsConstant;
import com.spring_boot_microservices.dto.AccountsDto;
import com.spring_boot_microservices.dto.CustomerDto;
import com.spring_boot_microservices.dto.ResponseDto;
import com.spring_boot_microservices.service.IAccountsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class AccountsController {
    private final IAccountsService accountsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
        accountsService.create(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstant.STATUS_200,AccountsConstant.STATUS_201));
    }
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber) {
        CustomerDto customerDto=accountsService.getAccountByMobileNumber(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }
}
