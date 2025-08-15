package com.microservices.accounts.controller;

import com.microservices.accounts.dto.CustomerDetailsDto;
import com.microservices.accounts.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {
    private final ICustomerService customerService;
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestParam String mobileNumber) {
        CustomerDetailsDto customerDetailsDto= customerService.getCustomerDetails(mobileNumber);
        return ResponseEntity.ok().body(customerDetailsDto);
    }
}
