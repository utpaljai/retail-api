package com.myorg.retail.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.myorg.retail.exception.CustomerNotFoundException;
import com.myorg.retail.fixtures.CustomerRewardPointFixture;
import com.myorg.retail.service.CustomerService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetRewardPointsByCustomerId_success() throws Exception {
        Mockito.when(customerService.getRewardPointsByCustomerId(any()))
                .thenReturn(CustomerRewardPointFixture.getCustomerRewardPoints());

        mockMvc.perform(get("/customers/1/rewards").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("customerId").value("1")).andExpect(jsonPath("customerName").value("name1"))
                .andExpect(jsonPath("totalPoints").value("320"));
    }

    @Test
    public void testGetRewardPointsByCustomerId_notFound() throws Exception {
        Mockito.when(customerService.getRewardPointsByCustomerId(any())).thenThrow(CustomerNotFoundException.class);

        mockMvc.perform(get("/customers/1/rewards").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetRewardPointsByCustomerName_success() throws Exception {
        Mockito.when(customerService.getRewardPointsByNameOrAllCustomers(any()))
                .thenReturn(CustomerRewardPointFixture.getCustomerRewardPointsList());

        mockMvc.perform(get("/customers/rewards").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerId").value("1"))
                .andExpect(jsonPath("$[0].customerName").value("name1"))
                .andExpect(jsonPath("$[0].totalPoints").value("320"));
    }

    @Test
    public void testGetRewardPoints_success() throws Exception {
        Mockito.when(customerService.getRewardPointsByNameOrAllCustomers(any()))
                .thenReturn(CustomerRewardPointFixture.getCustomerRewardPointsList());

        mockMvc.perform(get("/customers/rewards").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerId").value("1"))
                .andExpect(jsonPath("$[0].customerName").value("name1"))
                .andExpect(jsonPath("$[0].totalPoints").value("320"));
    }

}
