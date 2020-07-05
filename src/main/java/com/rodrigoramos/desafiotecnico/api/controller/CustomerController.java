package com.rodrigoramos.desafiotecnico.api.controller;

import com.rodrigoramos.desafiotecnico.api.dto.CustomerNewDTO;
import com.rodrigoramos.desafiotecnico.api.mapper.CustomerMapper;
import com.rodrigoramos.desafiotecnico.api.model.Customer;
import com.rodrigoramos.desafiotecnico.api.service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping({"api/v1/customer"})
public class CustomerController {

    private final CustomerServiceImpl customerService;
    private final CustomerMapper mapper;

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CustomerNewDTO customerNewDTO) {
        Customer customer = mapper.toModel(customerNewDTO);
        customer = customerService.save(customer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Customer> find(@PathVariable Long id) {
        Customer customer = customerService.find(id);
        return ResponseEntity.ok().body(customer);
    }


    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        List<Customer> customerList = customerService.findAll();
        return ResponseEntity.ok().body(customerList);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @Valid @RequestBody CustomerNewDTO customerNewDTO) {
        Customer customer = customerService.find(id);
        BeanUtils.copyProperties(customerNewDTO, customer, "id");
        customerService.save(customer);
        return ResponseEntity.ok(customer);
    }

    @GetMapping(value = "/count")
    public Long getNumberOfCustomers() {
        return customerService.getNumberOfCustomers();
    }


}
