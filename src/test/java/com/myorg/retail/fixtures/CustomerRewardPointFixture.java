package com.myorg.retail.fixtures;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.myorg.retail.models.CustomerRewardPoints;

public class CustomerRewardPointFixture {

    public static CustomerRewardPoints getCustomerRewardPoints() {
        CustomerRewardPoints reward = new CustomerRewardPoints();
        reward.setCustomerId(1);
        reward.setCustomerName("name1");
        reward.setTotalPoints(320);

        Map<LocalDate, Integer> pointByMonthMap1 = new HashMap<>();

        pointByMonthMap1.put(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()), 110);
        pointByMonthMap1.put(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).minusDays(3), 100);
        pointByMonthMap1.put(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).minusMonths(2), 110);

        reward.setPointsByMonthMap(pointByMonthMap1);

        return reward;
    }

    public static List<CustomerRewardPoints> getCustomerRewardPointsList() {
        List<CustomerRewardPoints> rewards = new ArrayList<>();
        rewards.add(getCustomerRewardPoints());
        return rewards;
    }

}
