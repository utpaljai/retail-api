package com.myorg.retail.util;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.myorg.retail.entity.Order;

public class RewardPointCalculator {

    public static Map<LocalDate, Integer> convertOrdersToPointsByMonthMap(Set<Order> orders) {
        Map<LocalDate, List<Order>> ordersByMonthMap = orders.stream().collect(Collectors
                .groupingBy(order -> order.getTransactionCreatedDate().with(TemporalAdjusters.firstDayOfMonth())));
        return convertMonthlyOrdersToPoints(ordersByMonthMap);
    }

    private static Map<LocalDate, Integer> convertMonthlyOrdersToPoints(Map<LocalDate, List<Order>> map) {
        Map<LocalDate, Integer> newMap = new HashMap<>();
        for (Map.Entry<LocalDate, List<Order>> entry : map.entrySet()) {
            newMap.put(entry.getKey(), convertMonthlyAmountToPoints(entry.getValue()));
        }
        return newMap;
    }

    private static Integer convertMonthlyAmountToPoints(List<Order> ordersByMonth) {
        return ordersByMonth.stream().map(order -> calculateRewardPoint(order.getTransactionAmount()))
                .collect(Collectors.summingInt(Integer::intValue));
    }

    private static Integer calculateRewardPoint(float transactionAmount) {
        if (transactionAmount > 50 && transactionAmount <= 100) {
            return Math.round(transactionAmount - 50);
        } else if (transactionAmount > 100) {
            return Math.round(transactionAmount - 100) * 2 + 50;
        }
        return 0;
    }

}
