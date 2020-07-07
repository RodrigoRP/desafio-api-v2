package com.rodrigoramos.desafiotecnico.api.controller;

import com.jayway.jsonpath.JsonPath;
import com.rodrigoramos.desafiotecnico.api.dto.CustomerNewDTO;
import com.rodrigoramos.desafiotecnico.api.model.Customer;
import com.rodrigoramos.desafiotecnico.api.service.CustomerServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class CustomerControllerTestV2 {

    @Mock
    CustomerServiceImpl customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    CustomerNewDTO customerNewDTO;
    Customer customer;

    @BeforeEach
    void setUp() {
        customerNewDTO = new CustomerNewDTO("87362800037", "Adamastor", "Rural");
        customer = new Customer(1L, "87362800037", "Adamastor", "Rural");

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void findCustomerByIdTest() throws Exception {
        given(customerService.find(1L)).willReturn(customer);

        mockMvc.perform(get("/api/v1/customer/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Adamastor")));
    }
}


//given

//when

//then