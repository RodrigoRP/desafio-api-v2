package com.rodrigoramos.desafiotecnico.api.service.interfaces;

import com.rodrigoramos.desafiotecnico.api.model.Sale;

import java.util.List;

public interface SaleService {

    Sale save(Sale sale);

    List<Sale> findAll();

    Long getIdMostExpensiveSale();

    String getWorstSalesman();

}
