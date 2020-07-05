package com.rodrigoramos.desafiotecnico.api.service.interfaces;

import com.rodrigoramos.desafiotecnico.api.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer save(Customer customer);

    List<Customer> findAll();

    Customer find(Long id);

    void delete(Long id);
}
