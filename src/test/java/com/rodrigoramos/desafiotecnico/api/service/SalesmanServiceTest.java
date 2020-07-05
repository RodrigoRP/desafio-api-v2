package com.rodrigoramos.desafiotecnico.api.service;

import com.rodrigoramos.desafiotecnico.api.model.Salesman;
import com.rodrigoramos.desafiotecnico.api.repository.SalesmanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SalesmanServiceTest {

    @InjectMocks
    private SalesmanServiceImpl service;

    @Mock
    private SalesmanRepository repository;

    @Test
    void saveSalesmanTest() {
        // Setup our mock repository
        Salesman salesman = new Salesman(null, "68424226046", "Adamastor", 3000.00);
        doReturn(salesman).when(repository).save(any());

        // Execute the service call
        Salesman save = service.save(salesman);

        // Assert the response
        Assertions.assertNotNull(save);
    }

    @Test
    void findSalesmanByNameTest() {
        // Setup our mock repository
        Salesman salesman = new Salesman(null, "68424226046", "Adamastor", 3000.00);
        doReturn(salesman).when(repository).findByName(salesman.getName());

        // Execute the service call
        Salesman returnedSalesman = service.findByName(salesman.getName());

        // Assert the response
        Assertions.assertEquals(returnedSalesman, salesman);

    }

    @Test
    void findAllSalesmanTest() {
        // Setup our mock repository
        Salesman salesman1 = new Salesman(null, "68424226046", "Adamastor", 3000.00);
        Salesman salesman2 = new Salesman(null, "33364109087", "Humberto", 15000.00);
        doReturn(Arrays.asList(salesman1, salesman2)).when(repository).findAll();

        // Execute the service call
        List<Salesman> salesmanList = service.findAll();

        // Assert the response
        Assertions.assertEquals(2, salesmanList.size());
    }

    @Test
    void findSalesmanByIdTest() {
        // Setup our mock repository
        Salesman salesman = new Salesman(1L, "68424226046", "Adamastor", 3000.00);
        doReturn(Optional.of(salesman)).when(repository).findById(1L);

        // Execute the service call
        Salesman returnedSalesman = service.find(1L);

        // Assert the response
        Assertions.assertEquals(returnedSalesman, salesman);

    }

    @Test
    void deleteSalesmanByIdTest() {
        // Setup our mock repository
        Salesman salesman = new Salesman(1L, "68424226046", "Adamastor", 3000.00);
        doReturn(Optional.of(salesman)).when(repository).findById(1L);

        // Execute the service call
        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
