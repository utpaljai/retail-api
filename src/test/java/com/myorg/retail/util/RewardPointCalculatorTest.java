package com.myorg.retail.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.myorg.retail.fixtures.OrderFixture;

public class RewardPointCalculatorTest {

    @Test
    public void testConvertOrdersToPointsByMonthMap() {
        Map<LocalDate, Integer> ordersByMonthMap = RewardPointCalculator
                .convertOrdersToPointsByMonthMap(OrderFixture.getOrders());

        assertEquals(3, ordersByMonthMap.size());
    }

}
