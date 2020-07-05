package com.rodrigoramos.desafiotecnico.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigoramos.desafiotecnico.api.model.Customer;
import com.rodrigoramos.desafiotecnico.api.repository.CustomerRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class CustomerControllerTest {

    private final String BASE_URL = "/api/v1/customer";

    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository mockRepository;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }


    @Test
    void find_id_200() throws Exception {
        Customer customer1 = new Customer(1L, "46470486000122", "Mario", "Rural");

        doReturn(Optional.of(customer1)).when(mockRepository).findById(1L);

        mockMvc.perform(get(BASE_URL + "/1"))
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.cnpj", is("46470486000122")))
                .andExpect(jsonPath("$.name", is("Mario")))
                .andExpect(jsonPath("$.businessArea", is("Rural")));

        verify(mockRepository, times(1)).findById(1L);
    }

    @Test
    void find_all_200() throws Exception {
        List<Customer> list = new ArrayList<>();
        Customer customer1 = new Customer(1L, "46470486000122", "Mario", "Rural");
        Customer customer2 = new Customer(2L, "41615065000129", "José", "Rural");

        doReturn(Arrays.asList(customer1, customer2)).when(mockRepository).findAll();

        mockMvc.perform(get(BASE_URL))
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].cnpj", is("46470486000122")))
                .andExpect(jsonPath("$[0].name", is("Mario")))
                .andExpect(jsonPath("$[0].businessArea", is("Rural")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].cnpj", is("41615065000129")))
                .andExpect(jsonPath("$[1].name", is("José")))
                .andExpect(jsonPath("$[1].businessArea", is("Rural")));

        verify(mockRepository, times(1)).findAll();
    }

}
