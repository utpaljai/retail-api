package com.myorg.retail.converter;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.myorg.retail.entity.Customer;
import com.myorg.retail.models.CustomerRewardPoints;
import com.myorg.retail.util.RewardPointCalculator;

@Component
public class CustomerToRewardPointConverter implements Converter<Customer, CustomerRewardPoints> {

    @Override
    public CustomerRewardPoints convert(Customer source) {
        CustomerRewardPoints customerRewardPoints = new CustomerRewardPoints();
        customerRewardPoints.setCustomerId(source.getCustomerId());
        customerRewardPoints.setCustomerName(source.getCustomerName());
        Map<LocalDate, Integer> pointsByMonthMap = RewardPointCalculator
                .convertOrdersToPointsByMonthMap(source.getOrders());
        Integer totalPoints = pointsByMonthMap.values().stream().collect(Collectors.summingInt(Integer::intValue));
        customerRewardPoints.setPointsByMonthMap(pointsByMonthMap);
        customerRewardPoints.setTotalPoints(totalPoints);
        return customerRewardPoints;
    }

}
