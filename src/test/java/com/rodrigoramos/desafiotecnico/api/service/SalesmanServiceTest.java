package com.rodrigoramos.desafiotecnico.api.service;

import com.rodrigoramos.desafiotecnico.api.model.Salesman;
import com.rodrigoramos.desafiotecnico.api.repository.SalesmanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SalesmanServiceTest {

    @InjectMocks
    private SalesmanServiceImpl service;

    @Mock
    private SalesmanRepository repository;

    @Test
    void saveSalesmanTest() {
        //given
        Salesman salesman = new Salesman();
        given(repository.save(any(Salesman.class))).willReturn(salesman);

        //when
        Salesman savedSalesman = service.save(salesman);

        //then
        Assertions.assertNotNull(savedSalesman);
        then(repository).should(times(1)).save(any(Salesman.class));
    }

    @Test
    void saveSalesmanAlreadytExistsTest() {
        //given
        Salesman salesman1 = new Salesman(1L, "78375158038", "Adamastor", 1223.00);
        Salesman salesman2 = new Salesman(2L, "85206466034", "Adamastor", 1223.00);
        given(repository.save(any(Salesman.class))).willReturn(salesman1);

        //when
        Salesman savedSalesman1 = service.save(salesman1);
        Salesman savedsalesman2 = service.save(salesman2);

        //then
        Assertions.assertEquals(savedSalesman1.getSalary(), savedsalesman2.getSalary());
        then(repository).should(times(2)).save(any(Salesman.class));
    }

    @Test
    void findSalesmanByNameTest() {
        //given
        Salesman salesman = new Salesman();
        given(repository.findByName(salesman.getName())).willReturn(salesman);

        //when
        Salesman returnedSalesman = service.findByName(salesman.getName());

        //then
        Assertions.assertEquals(returnedSalesman, salesman);
        then(repository).should(times(1)).findByName(salesman.getName());
    }

    @Test
    void findAllSalesmanTest() {
        //given
        Salesman salesman = new Salesman();
        List<Salesman> salesmen = new ArrayList<>();
        salesmen.add(salesman);
        given(repository.findAll()).willReturn(salesmen);

        //when
        List<Salesman> salesmanList = service.findAll();

        //then
        then(repository).should(times(1)).findAll();
        Assertions.assertEquals(1, salesmanList.size());
    }

    @Test
    void findSalesmanByIdTest() {
        //given
        Salesman salesman = new Salesman();
        given(repository.findById(1L)).willReturn(Optional.of(salesman));

        //when
        Salesman returnedSalesman = service.find(1L);

        //then
        Assertions.assertEquals(returnedSalesman, salesman);
        then(repository).should(times(1)).findById(anyLong());
    }

    @Test
    void deleteSalesmanByIdTest() {
        //given
        Salesman salesman = new Salesman();
        given(repository.findById(1L)).willReturn(Optional.of(salesman));

        //when
        service.delete(1L);

        //then
        then(repository).should(times(1)).deleteById(anyLong());
    }

    @Test
    void getNumberOfSalespeopleTest() {
        //given

        //when
        service.getNumberOfSalespeople();

        //then
        then(repository).should(times(1)).count();
    }
}
