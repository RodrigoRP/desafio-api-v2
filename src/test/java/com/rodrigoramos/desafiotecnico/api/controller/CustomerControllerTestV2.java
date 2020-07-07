package com.rodrigoramos.desafiotecnico.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
class CustomerControllerTestV2 {
    private final String BASE_URL = "/api/v1/customer";

    private ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Mock
    CustomerServiceImpl customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    CustomerNewDTO customerNewDTO;
    Customer customer;
    Customer customer1;
    Customer customer2;
    List<Customer> customerList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        customerNewDTO = new CustomerNewDTO("87362800037", "Adamastor", "Rural");
        customer = new Customer(1L, "87362800037", "Adamastor", "Rural");
        customer1 = new Customer(2L, "87362800037", "Jardel", "Rural");
        customer2 = new Customer(3L, "87362800037", "Ronaldo", "Rural");
        customerList.add(customer1);
        customerList.add(customer2);


        mockMvc = MockMvcBuilders
               // .webAppContextSetup(webApplicationContext)
                .standaloneSetup(customerController)
                .build();
    }

    @Test
    void findCustomerByIdTest() throws Exception {
        given(customerService.find(1L)).willReturn(customer);

        mockMvc.perform(get(BASE_URL + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Adamastor")));
    }

    @Test
    void findAllCustomerTest() throws Exception {
        given(customerService.findAll()).willReturn(customerList);

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", Matchers.is(2)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Jardel")))
                .andExpect(jsonPath("$[1].id", Matchers.is(3)))
                .andExpect(jsonPath("$[1].name", Matchers.is("Ronaldo")));
    }

    @Test
    void deletar_200() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/1"))
                .andExpect(status().isNoContent());

        verify(customerService, times(1)).delete(1L);
    }


}


//given

//when

//then