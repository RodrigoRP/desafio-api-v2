package com.rodrigoramos.desafiotecnico.api.service;

import com.rodrigoramos.desafiotecnico.api.dto.SaleNewDTO;
import com.rodrigoramos.desafiotecnico.api.model.Sale;
import com.rodrigoramos.desafiotecnico.api.model.Salesman;
import com.rodrigoramos.desafiotecnico.api.repository.SaleRepository;
import com.rodrigoramos.desafiotecnico.api.repository.SalesmanRepository;
import com.rodrigoramos.desafiotecnico.api.service.exceptions.ObjectNotFoundException;
import com.rodrigoramos.desafiotecnico.api.service.interfaces.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private final SalesmanRepository salesmanRepository;
    private final SaleRepository saleRepository;

    @Autowired
    public SaleServiceImpl(SalesmanRepository salesmanRepository,
                           SaleRepository saleRepository) {
        this.salesmanRepository = salesmanRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Sale convertToModel(SaleNewDTO saleNewDTO) {
        Salesman salesman = salesmanRepository.findByName(saleNewDTO.getSalesmanName());
        return new Sale(saleNewDTO.getSaleId(), saleNewDTO.getSaleItems(), salesman);
    }

    @Override
    public Long getIdMostExpensiveSale() {
        List<Sale> saleList = saleRepository.findAll();
        return saleList
                .stream()
                .max(Comparator.comparing(Sale::getValue))
                .orElseThrow(() -> new ObjectNotFoundException("No recorded sales!!"))
                .getId();
    }

    @Override
    public String getWorstSalesman() {
        Map<Salesman, Double> salesAmount = getSalesAmountBySalesman(groupBySalesman());

        Salesman minSumSale = salesAmount
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow(() -> new ObjectNotFoundException("No salesman registered!!"))
                .getKey();

        return minSumSale.getName();
    }

    public Map<Salesman, List<Sale>> groupBySalesman() {
        List<Sale> saleList = saleRepository.findAll();

        return saleList.stream().collect(Collectors.groupingBy(Sale::getSalesmanName));
    }

    private Map<Salesman, Double> getSalesAmountBySalesman(Map<Salesman, List<Sale>> salesBySalesman) {
        Map<Salesman, Double> totalBySalesman = new HashMap<>();

        salesBySalesman.forEach((salesman, sellList) -> {
            totalBySalesman.put(salesman, getTotalSellList(sellList));
        });

        return totalBySalesman;
    }

    private Double getTotalSellList(List<Sale> salesBySalesman) {
        return salesBySalesman.stream().mapToDouble(Sale::getValue).sum();
    }


}
