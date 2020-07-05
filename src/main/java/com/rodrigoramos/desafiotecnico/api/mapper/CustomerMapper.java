package com.rodrigoramos.desafiotecnico.api.mapper;


import com.rodrigoramos.desafiotecnico.api.dto.CustomerNewDTO;
import com.rodrigoramos.desafiotecnico.api.model.Customer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final ModelMapper modelMapper;

    public Customer toModel(CustomerNewDTO customerNewDTO) {
        return modelMapper.map(customerNewDTO, Customer.class);
    }
}