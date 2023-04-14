package com.myorg.retail.fixtures;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.myorg.retail.entity.Customer;
import com.myorg.retail.entity.Order;

public class CustomerEntityFixture {
    public static Customer getCustomerEntity() {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setCustomerName("name");

        Order order1 = new Order();
        order1.setCustomer(customer);
        order1.setOrderId(101);
        order1.setTransactionAmount(120);
        order1.setTransactionCreatedDate(LocalDate.now());

        Order order2 = new Order();
        order2.setCustomer(customer);
        order2.setOrderId(102);
        order2.setTransactionAmount(160);
        order2.setTransactionCreatedDate(LocalDate.now().minus(70, ChronoUnit.DAYS));
        Set<Order> orders = new HashSet<>();
        orders.add(order1);
        orders.add(order2);
        customer.setOrders(orders);
        return customer;
    }

    public static List<Customer> getCustomerEntityList() {
        List<Customer> customers = new ArrayList<>();
        customers.add(getCustomerEntity());
        return customers;
    }

}
