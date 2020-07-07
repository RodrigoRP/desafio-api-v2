package com.rodrigoramos.desafiotecnico.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodrigoramos.desafiotecnico.api.dto.CustomerNewDTO;
import com.rodrigoramos.desafiotecnico.api.model.Customer;
import com.rodrigoramos.desafiotecnico.api.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CustomerControllerTest {

    private final String BASE_URL = "/api/v1/customer";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private ObjectMapper objectMapper;

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

    @Test
    void deletar_200() throws Exception {
        Customer customer1 = new Customer(1L, "46470486000122", "Mario", "Rural");

        when(mockRepository.findById(1L)).thenReturn(Optional.of(customer1));

        mockMvc.perform(delete(BASE_URL + "/1"))
                .andExpect(status().isNoContent());

        verify(mockRepository, times(1)).deleteById(1L);
    }

    @Test
    void criar_204() throws Exception {
        CustomerNewDTO dto = new CustomerNewDTO("46470486000122", "Mario", "Rural");
        Customer customer1 = new Customer(1L, "46470486000122", "Mario", "Rural");

        //when(mockRepository.save(any(Customer.class))).thenReturn(customer1);
        doReturn(customer1).when(mockRepository).save(any());

        mockMvc.perform(post(BASE_URL)
                .content(objectMapper.writeValueAsString(dto))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        //  verify(mockRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void update_200() throws Exception {
        Customer customer1 = new Customer(1L, "46470486000122", "Mario", "Rural");
        CustomerNewDTO dto = new CustomerNewDTO("09938837000150", "Adão", "Urbano");

        doReturn(Optional.of(customer1)).when(mockRepository).findById(1L);

        mockMvc.perform(put(BASE_URL + "/{id}", 1)
                .content(objectMapper.writeValueAsString(dto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Adão"));
    }

    @Test
    void getNumberOfCustomers_200()throws Exception {
        Customer customer1 = new Customer(1L, "46470486000122", "Mario", "Rural");

        doReturn(Optional.of(customer1)).when(mockRepository).findById(1L);

        mockMvc.perform(get(BASE_URL + "/count")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
