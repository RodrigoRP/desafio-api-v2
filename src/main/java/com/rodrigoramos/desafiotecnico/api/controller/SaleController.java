package com.rodrigoramos.desafiotecnico.api.controller;


import com.rodrigoramos.desafiotecnico.api.dto.SaleNewDTO;
import com.rodrigoramos.desafiotecnico.api.mapper.SaleMapper;
import com.rodrigoramos.desafiotecnico.api.model.Sale;
import com.rodrigoramos.desafiotecnico.api.service.DatabaseService;
import com.rodrigoramos.desafiotecnico.api.service.SaleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping({"api/v1/sale"})
public class SaleController {

    private final SaleServiceImpl saleService;
    private final DatabaseService databaseService;
    private final SaleMapper saleMapper;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SaleNewDTO saleNewDTO) {
        Sale sale = saleMapper.toModel(saleNewDTO);
        sale = saleService.save(sale);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(sale.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Sale>> findAll() {
        List<Sale> saleList = saleService.findAll();
        return ResponseEntity.ok().body(saleList);
    }

    @GetMapping(value = "/expensive")
    public ResponseEntity<Long> getIdMostExpensiveSale() {
        Long idMostExpensiveSale = saleService.getIdMostExpensiveSale();
        return ResponseEntity.ok().body(idMostExpensiveSale);
    }

    @GetMapping(value = "/reportGenerate")
    public ResponseEntity<Void> reportGenerate() {
        databaseService.generateReport();
        return ResponseEntity.noContent().build();
    }


}
