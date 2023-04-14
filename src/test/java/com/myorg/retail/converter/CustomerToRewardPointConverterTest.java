package com.myorg.retail.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myorg.retail.fixtures.CustomerEntityFixture;
import com.myorg.retail.models.CustomerRewardPoints;

@ExtendWith(MockitoExtension.class)
public class CustomerToRewardPointConverterTest {
    @InjectMocks
    private CustomerToRewardPointConverter customerToRewardPointConverter;

    @Test
    public void testCustomerToRewardPointConverter() {
        CustomerRewardPoints customerRewardPoints = customerToRewardPointConverter
                .convert(CustomerEntityFixture.getCustomerEntity());
        assertNotNull(customerRewardPoints);
        assertEquals(260, customerRewardPoints.getTotalPoints());
        assertEquals(2, customerRewardPoints.getPointsByMonthMap().size());
    }

}
