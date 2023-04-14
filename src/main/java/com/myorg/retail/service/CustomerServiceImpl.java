package com.myorg.retail.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.myorg.retail.converter.CustomerToRewardPointConverter;
import com.myorg.retail.exception.CustomerNotFoundException;
import com.myorg.retail.models.CustomerRewardPoints;
import com.myorg.retail.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    private CustomerToRewardPointConverter customerToRewardPointConverter;

    @Autowired
    public CustomerServiceImpl(final CustomerRepository customerRepository,
            final CustomerToRewardPointConverter customerToRewardPointConverter) {
        this.customerRepository = customerRepository;
        this.customerToRewardPointConverter = customerToRewardPointConverter;
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerRewardPoints getRewardPointsByCustomerId(Integer customerId) {
        return customerRepository.findById(customerId).map(customerToRewardPointConverter::convert)
                .orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerRewardPoints> getRewardPointsByNameOrAllCustomers(String customerName) {
        if (Objects.isNull(customerName)) {
            return customerRepository.findAll().stream().map(customerToRewardPointConverter::convert)
                    .collect(Collectors.toList());
        }
        return customerRepository.findByCustomerName(customerName).stream().map(customerToRewardPointConverter::convert)
                .collect(Collectors.toList());
    }

}
