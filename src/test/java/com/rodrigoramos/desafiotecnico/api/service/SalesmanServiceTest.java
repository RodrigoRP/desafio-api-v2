package com.rodrigoramos.desafiotecnico.api.service;


import com.rodrigoramos.desafiotecnico.api.dto.SalesmanNewDTO;
import com.rodrigoramos.desafiotecnico.api.model.Salesman;
import com.rodrigoramos.desafiotecnico.api.repository.SalesmanRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
@DirtiesContext
public class SalesmanServiceTest {

    @Autowired
    private SalesmanServiceImpl salesmanService;

    @Autowired
    private SalesmanRepository salesmanRepository;

    @Test
    public void shouldCreateSalesman() {
        // given
        Salesman salesman = new Salesman(1L, "85424488000137", "João", 45000.00);

        // when
        Salesman savedSalesman = salesmanService.save(salesman);

        // then

        assertSoftly(softly -> {
            softly.assertThat(savedSalesman).isNotNull();
            softly.assertThat(savedSalesman.getSalary()).isEqualTo(salesman.getSalary());
            softly.assertThat(savedSalesman.getName()).isEqualTo(salesman.getName());
            softly.assertThat(savedSalesman.getId()).isNotNull();
            softly.assertThat(savedSalesman.getCpf()).isEqualTo(salesman.getCpf());
        });
    }

    @Test
    public void shouldDeleteSalesman() {
        // given
        Salesman salesman = new Salesman(null, "85424488000137", "João", 45000.00);
        salesmanService.save(salesman);
        assertThat(salesmanRepository.findById(salesman.getId())).isNotNull();

        // when
        salesmanService.delete(salesman.getId());

        // then
        assertThat(salesmanRepository.findById(salesman.getId())).isNotPresent();

    }


    @Test
    public void shouldFindById() {
        // given
        Salesman savedSalesman = new Salesman(null, "85424488000137", "João", 45000.00);
        salesmanService.save(savedSalesman);

        // when
        Salesman foundSalesman = salesmanService.find(savedSalesman.getId());

        // then
        assertThat(foundSalesman).isNotNull();
        assertThat(foundSalesman.getId()).isEqualTo(savedSalesman.getId());

    }

    @Test
    public void shouldFindByName() {
        // given
        Salesman salesman = new Salesman(1L, "85424488000137", "João", 45000.00);
        Salesman savedSalesman = salesmanService.save(salesman);
        // when
        Salesman foundSalesman = salesmanService.findByName(savedSalesman.getName());

        // then
        assertThat(foundSalesman).isNotNull();
        assertThat(foundSalesman.getName()).isEqualTo(savedSalesman.getName());

    }


    @Test
    public void shouldFindAll() {
        // given
        Salesman savedSalesman1 = new Salesman(null, "85424488000137", "João", 45000.00);
        Salesman savedSalesman2 = new Salesman(null, "85424488000137", "Maria", 45000.00);

        salesmanService.save(savedSalesman1);
        salesmanService.save(savedSalesman2);


        // when
        List<Salesman> salesmen = salesmanService.findAll();

        // then
        assertThat(salesmen.stream().map(Salesman::getId).collect(Collectors.toList()))
                .contains(savedSalesman1.getId(), savedSalesman2.getId());

    }

    @Test
    public void shouldReturnNumberOfSalesman() {
        // given
        Salesman savedSalesman1 = new Salesman(null, "85424488000137", "João", 45000.00);
        Salesman savedSalesman2 = new Salesman(null, "85424488000137", "Maria", 45000.00);

        salesmanService.save(savedSalesman1);
        salesmanService.save(savedSalesman2);

        // when
        Long foundCustomer = salesmanService.getNumberOfSalespeople();
        List<Salesman> salesmen = salesmanService.findAll();

        // then
        assertThat(foundCustomer).isNotNull();
        assertThat(salesmen.size()).isEqualTo(foundCustomer.intValue());
    }


    @Test
    public void shouldConvertDtoToModel() {
        // given
        Salesman expected = new Salesman(null, "85424488000137", "João", 45000.00);

        // when
        SalesmanNewDTO dto = new SalesmanNewDTO(expected.getCpf(), expected.getName(), expected.getSalary());

        // then
        assertThat(expected).isEqualToComparingFieldByField(salesmanService.convertToModel(dto));
    }



}
