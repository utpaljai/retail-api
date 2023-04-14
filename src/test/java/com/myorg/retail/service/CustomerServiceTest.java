package com.myorg.retail.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myorg.retail.converter.CustomerToRewardPointConverter;
import com.myorg.retail.exception.CustomerNotFoundException;
import com.myorg.retail.fixtures.CustomerEntityFixture;
import com.myorg.retail.fixtures.CustomerRewardPointFixture;
import com.myorg.retail.models.CustomerRewardPoints;
import com.myorg.retail.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @InjectMocks
    private CustomerServiceImpl service;

    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerToRewardPointConverter customerToRewardPointConverter;

    @Test
    public void testGetRewardPointsByCustomerId_success() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(CustomerEntityFixture.getCustomerEntity()));

        Mockito.when(customerToRewardPointConverter.convert(any()))
                .thenReturn(CustomerRewardPointFixture.getCustomerRewardPoints());

        CustomerRewardPoints rewardPoints = service.getRewardPointsByCustomerId(1);
        assertNotNull(rewardPoints);
        assertEquals(320, rewardPoints.getTotalPoints());
    }

    @Test
    public void testGetRewardPointsByCustomerName_success() {
        Mockito.when(repository.findByCustomerName(any())).thenReturn(CustomerEntityFixture.getCustomerEntityList());

        Mockito.when(customerToRewardPointConverter.convert(any()))
                .thenReturn(CustomerRewardPointFixture.getCustomerRewardPoints());

        List<CustomerRewardPoints> rewardPoints = service.getRewardPointsByNameOrAllCustomers("name1");
        assertEquals(1, rewardPoints.size());
        assertEquals(320, rewardPoints.get(0).getTotalPoints());
    }

    @Test
    public void testGetRewardPoints_success() {
        Mockito.when(repository.findAll()).thenReturn(CustomerEntityFixture.getCustomerEntityList());

        Mockito.when(customerToRewardPointConverter.convert(any()))
                .thenReturn(CustomerRewardPointFixture.getCustomerRewardPoints());

        List<CustomerRewardPoints> rewardPoints = service.getRewardPointsByNameOrAllCustomers(null);
        assertNotNull(rewardPoints);
        assertEquals(1, rewardPoints.size());
        assertEquals(320, rewardPoints.get(0).getTotalPoints());
    }

    @Test
    public void testGetRewardPointsByCustomerId_notFound() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> {
            service.getRewardPointsByCustomerId(1);
        });

    }

}
