package com.myorg.retail.models;

import java.time.LocalDate;
import java.util.Map;

public class CustomerRewardPoints {
    private Integer customerId;
    private String customerName;
    private Map<LocalDate, Integer> pointsByMonthMap;
    private Integer totalPoints;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Map<LocalDate, Integer> getPointsByMonthMap() {
        return pointsByMonthMap;
    }

    public void setPointsByMonthMap(Map<LocalDate, Integer> pointsByMonthMap) {
        this.pointsByMonthMap = pointsByMonthMap;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

}
