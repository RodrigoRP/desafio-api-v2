package com.rodrigoramos.desafiotecnico.api.service;

import com.rodrigoramos.desafiotecnico.api.model.Sale;
import com.rodrigoramos.desafiotecnico.api.model.SaleItem;
import com.rodrigoramos.desafiotecnico.api.model.Salesman;
import com.rodrigoramos.desafiotecnico.api.repository.SaleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
@DirtiesContext
public class SaleServiceTest {


    @Autowired
    private SaleServiceImpl saleService;

    @Autowired
    private SalesmanServiceImpl salesmanService;


    @Autowired
    private SaleRepository saleRepository;

    @Test
    public void shouldReturnWorstSalesman() {
        // given
        Salesman salesman1 = new Salesman(null, "43125821061", "Maria", 45000.00);
        Salesman salesman1Saved = salesmanService.save(salesman1);
        Salesman salesman2 = new Salesman(null, "43125821061", "Edward is the worst salesman", 45000.00);
        Salesman salesman2Saved = salesmanService.save(salesman2);


        SaleItem saleItem1 = new SaleItem(null, 1, 0, -1.00);
        SaleItem saleItem2 = new SaleItem(null, 1, 1, 100.0);
        List<SaleItem> items1 = new ArrayList<>();
        items1.add(saleItem1);

        List<SaleItem> items2 = new ArrayList<>();
        items2.add(saleItem2);


        Sale sale1 = new Sale(1L, items2, salesman1);
        Sale sale1Saved = saleService.save(sale1);
        Sale sale2 = new Sale(2L, items1, salesman2);
        Sale sale2Saved = saleService.save(sale2);


        // when
        String expected = salesman2.getName();
        String nameWorstSalesman = saleService.getWorstSalesman();

        // then
        assertThat(expected).isEqualTo(nameWorstSalesman);

    }


}
