package com.microservices.accounts.controller;

import com.microservices.accounts.dto.CustomerDetailsDto;
import com.microservices.accounts.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {
    private final ICustomerService customerService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("eazybank-correlation-id") String correlationId, @RequestParam String mobileNumber) {
        logger.debug("eazyBank-correlation-id found: {} ", correlationId);
        CustomerDetailsDto customerDetailsDto= customerService.getCustomerDetails(mobileNumber,correlationId);
        return ResponseEntity.ok().body(customerDetailsDto);
    }
}
