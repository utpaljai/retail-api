package com.myorg.retail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myorg.retail.models.CustomerRewardPoints;
import com.myorg.retail.service.CustomerService;

@RestController
@RequestMapping("")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/customers/{id}/rewards")
    public ResponseEntity<CustomerRewardPoints> getCustomerRewardSummaryById(@PathVariable("id") Integer customerId) {
        CustomerRewardPoints reward = customerService.getRewardPointsByCustomerId(customerId);
        return ResponseEntity.ok().body(reward);
    }

    @GetMapping(value = "/customers/rewards")
    public ResponseEntity<List<CustomerRewardPoints>> getCustomerRewardSummaryByName(
            @RequestParam(value = "customer-name", required = false) String customerName) {
        List<CustomerRewardPoints> rewards = customerService.getRewardPointsByNameOrAllCustomers(customerName);

        return ResponseEntity.ok().body(rewards);
    }

}
