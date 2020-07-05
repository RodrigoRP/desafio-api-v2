package com.rodrigoramos.desafiotecnico.api.controller;

import com.rodrigoramos.desafiotecnico.api.dto.SalesmanNewDTO;
import com.rodrigoramos.desafiotecnico.api.mapper.SalesmanMapper;
import com.rodrigoramos.desafiotecnico.api.model.Salesman;
import com.rodrigoramos.desafiotecnico.api.service.SaleServiceImpl;
import com.rodrigoramos.desafiotecnico.api.service.SalesmanServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping({"api/v1/salesman"})
public class SalesmanController {

    private final SaleServiceImpl saleService;
    private final SalesmanServiceImpl salesmanService;
    private final SalesmanMapper salesmanMapper;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody SalesmanNewDTO salesmanNewDTO) {
        Salesman salesman = salesmanMapper.toModel(salesmanNewDTO);
        salesman = salesmanService.save(salesman);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(salesman.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<Salesman>> findAll() {
        List<Salesman> salesmanList = salesmanService.findAll();
        return ResponseEntity.ok().body(salesmanList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Salesman> find(@PathVariable Long id) {
        Salesman salesman = salesmanService.find(id);
        return ResponseEntity.ok().body(salesman);
    }

    @GetMapping(value = "/count")
    public Long getNumberOfSalespeople() {
        return salesmanService.getNumberOfSalespeople();
    }

    @GetMapping(value = "/worst")
    public ResponseEntity<Salesman> getWorstSalesman() {
        String worstSalesman = saleService.getWorstSalesman();
        Salesman salesman = salesmanService.findByName(worstSalesman);
        return ResponseEntity.ok().body(salesman);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        salesmanService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Salesman> update(@PathVariable Long id, @Valid @RequestBody SalesmanNewDTO salesmanNewDTO) {
        Salesman salesman = salesmanService.find(id);
        BeanUtils.copyProperties(salesmanNewDTO, salesman, "id");
        salesmanService.save(salesman);
        return ResponseEntity.ok(salesman);
    }


}
