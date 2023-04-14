package com.myorg.retail.service;

import java.util.List;
import com.myorg.retail.models.CustomerRewardPoints;

public interface CustomerService {
    /**
     * Get customer reward summary by customer Id.
     * 
     * @param customerId
     * 
     * @return CustomerRewardPoints contains reward summary grouped by month and total reward points
     */
    public CustomerRewardPoints getRewardPointsByCustomerId(Integer customerId);

    /**
     * Get customer reward summary by customer name. If customer name is not passed, then reward summary is returned for
     * all customers.
     * 
     * @param customerName
     *            optional name
     * 
     * @return List<CustomerRewardPoints> contains reward summary grouped by month and total reward points
     */
    public List<CustomerRewardPoints> getRewardPointsByNameOrAllCustomers(String customerName);

}
