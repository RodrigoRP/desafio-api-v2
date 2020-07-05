package com.rodrigoramos.desafiotecnico.api.service;


import com.rodrigoramos.desafiotecnico.api.model.Customer;
import com.rodrigoramos.desafiotecnico.api.repository.CustomerRepository;
import com.rodrigoramos.desafiotecnico.api.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerServiceImpl service;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    public void findCustomerByIdTest() {
        // Setup our mock repository
        Customer customer = new Customer(1L, "123123", "Mario", "Rural");
        doReturn(Optional.of(customer)).when(customerRepository).findById(1L);

        // Execute the service call
        Customer returnedCustomer = service.find(1L);

        // Assert the response
        Assertions.assertEquals(returnedCustomer, customer);
    }

    @Test
    public void shouldThrowErrorWhenFindByIdTest() {
        // Setup our mock repository
        doReturn(Optional.empty()).when(customerRepository).findById(1L);

        // Execute the service call
        //   Customer returnedCustomer = service.find(1L);

        // Assert the response
        Assertions.assertThrows(ObjectNotFoundException.class, () -> service.find(1L));
    }

    @Test
    public void findAllCustomerTest() {
        // Setup our mock repository
        Customer customer1 = new Customer(1L, "123123", "Mario", "Rural");
        Customer customer2 = new Customer(2L, "123123", "José", "Rural");

        doReturn(Arrays.asList(customer1, customer2)).when(customerRepository).findAll();

        // Execute the service call
        List<Customer> customerList = service.findAll();

        // Assert the response
        Assertions.assertEquals(2, customerList.size());
    }

    @Test
    public void saveCustomerTest() {
        // Setup our mock repository
        Customer customer = new Customer(1L, "123123", "Mario", "Rural");
        doReturn(customer).when(customerRepository).save(any());

        // Execute the service call
        Customer returnedCustomer = service.save(customer);

        // Assert the response
        Assertions.assertNotNull(returnedCustomer);
    }

    @Test
    public void deleteCustomerTest() {
        // Setup our mock repository
        Customer customer = new Customer(1L, "123123", "Mario", "Rural");
        doReturn(Optional.of(customer)).when(customerRepository).findById(1L);

        // Execute the service call
        service.delete(1L);

        verify(customerRepository, times(1)).deleteById(1L);
    }

/*    @Test
    public void getNumberOfCustomersTest() {
        // Setup our mock repository
        Customer customer1 = new Customer(null, "30938355000197", "Mario", "Rural");
        Customer customer2 = new Customer(null, "35973881000101", "José", "Rural");

        doReturn(Arrays.asList(customer1, customer2)).when(customerRepository).findAll();

        // Execute the service call
        service.save(customer1);
        service.save(customer2);
        Long numberOfCustomers = service.getNumberOfCustomers();

        // Assert the response
        Assertions.assertEquals(2L, numberOfCustomers);
    }*/


}
