package com.rodrigoramos.desafiotecnico.api.parser;

import com.rodrigoramos.desafiotecnico.api.model.Customer;

import java.util.StringTokenizer;

public class CustomerParser {

    public static Customer parse(StringTokenizer tokenizer) {
        Customer customer = new Customer();
        customer.setCnpj(tokenizer.nextToken());
        customer.setName(tokenizer.nextToken());
        customer.setBusinessArea(tokenizer.nextToken());
        return customer;
    }

    private CustomerParser() {
    }
}
