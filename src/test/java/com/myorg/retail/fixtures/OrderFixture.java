package com.myorg.retail.fixtures;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.myorg.retail.entity.Order;

public class OrderFixture {
    public static Set<Order> getOrders() {
        Set<Order> orders = new HashSet<>();
        Order order1 = new Order();
        order1.setOrderId(1);
        order1.setTransactionAmount(120);
        order1.setTransactionCreatedDate(LocalDate.now().minusMonths(3));
        orders.add(order1);

        Order order2 = new Order();
        order2.setOrderId(2);
        order2.setTransactionAmount(270);
        order2.setTransactionCreatedDate(LocalDate.now().minusMonths(2));
        orders.add(order2);

        Order order3 = new Order();
        order3.setOrderId(3);
        order3.setTransactionAmount(570);
        order3.setTransactionCreatedDate(LocalDate.now().minusDays(2));
        orders.add(order3);

        Order order4 = new Order();
        order4.setOrderId(4);
        order4.setTransactionAmount(70);
        order4.setTransactionCreatedDate(LocalDate.now());
        orders.add(order4);

        return orders;

    }

}
