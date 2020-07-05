package com.rodrigoramos.desafiotecnico.api.service;


import com.rodrigoramos.desafiotecnico.api.model.Customer;
import com.rodrigoramos.desafiotecnico.api.model.Sale;
import com.rodrigoramos.desafiotecnico.api.model.SaleItem;
import com.rodrigoramos.desafiotecnico.api.model.Salesman;
import com.rodrigoramos.desafiotecnico.api.repository.SaleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class SaleServiceTest {

    @InjectMocks
    private SaleServiceImpl saleService;

    @Mock
    private SaleRepository saleRepository;

    @Test
    void saveSaleTest() {
        // Setup our mock repository
        Salesman salesman1 = new Salesman(null, "68424226046", "Adamastor", 3000.00);
        SaleItem item1 = new SaleItem(1L, 10, 10, 10.00);
        SaleItem item2 = new SaleItem(2L, 11, 10, 10.00);
        List<SaleItem> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        Sale sale = new Sale(null, itemList, salesman1);
        doReturn(sale).when(saleRepository).save(any());

        // Execute the service call
        Sale savedSale = saleService.save(sale);

        // Assert the response
        Assertions.assertNotNull(savedSale);
    }

    @Test
    void findAllSaleTest() {

        // Setup our mock repository
        Salesman salesman1 = new Salesman(null, "68424226046", "Adamastor", 3000.00);
        SaleItem item1 = new SaleItem(1L, 10, 10, 10.00);
        SaleItem item2 = new SaleItem(2L, 11, 10, 10.00);
        List<SaleItem> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        Sale sale = new Sale(null, itemList, salesman1);
        doReturn(Arrays.asList(sale)).when(saleRepository).findAll();

        // Execute the service call
        List<Sale> saleList = saleService.findAll();

        // Assert the response
        Assertions.assertEquals(1, saleList.size());

    }

    @Test
    void getWorstSalesmanTest() {
        // Setup our mock repository
        Salesman salesman1 = new Salesman(null, "68424226046", "Adamastor", 3000.00);
        Salesman salesman2 = new Salesman(null, "33364109087", "Humberto", 15000.00);

        SaleItem item1 = new SaleItem(1L, 10, 10, 1.00);
        SaleItem item3 = new SaleItem(3L, 12, 100, 10.00);

        List<SaleItem> itemList1 = new ArrayList<>();
        itemList1.add(item1);

        List<SaleItem> itemList2 = new ArrayList<>();
        itemList2.add(item3);

        Sale sale1 = new Sale(null, itemList1, salesman1);
        Sale sale2 = new Sale(null, itemList2, salesman2);

        doReturn(Arrays.asList(sale1, sale2)).when(saleRepository).findAll();

        // Execute the service call
        String worstSalesman = saleService.getWorstSalesman();

        // Assert the response
        Assertions.assertEquals("Adamastor", worstSalesman);
    }

    @Test
    void getIdMostExpensiveSaleTest() {
        // Setup our mock repository
        Salesman salesman1 = new Salesman(null, "68424226046", "Adamastor", 3000.00);
        Salesman salesman2 = new Salesman(null, "33364109087", "Humberto", 15000.00);

        SaleItem item1 = new SaleItem(1L, 10, 10, 1.00);
        SaleItem item3 = new SaleItem(3L, 12, 100, 10.00);

        List<SaleItem> itemList1 = new ArrayList<>();
        itemList1.add(item1);

        List<SaleItem> itemList2 = new ArrayList<>();
        itemList2.add(item3);

        Sale sale1 = new Sale(1L, itemList1, salesman1);
        Sale sale2 = new Sale(2L, itemList2, salesman2);

        doReturn(Arrays.asList(sale1, sale2)).when(saleRepository).findAll();

        // Execute the service call
        Long idMostExpensiveSale = saleService.getIdMostExpensiveSale();

        // Assert the response
        Assertions.assertEquals(2L, idMostExpensiveSale);
    }


}
