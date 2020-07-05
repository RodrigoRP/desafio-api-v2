package com.rodrigoramos.desafiotecnico.api.mapper;

import com.rodrigoramos.desafiotecnico.api.dto.SalesmanNewDTO;
import com.rodrigoramos.desafiotecnico.api.model.Salesman;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class SalesmanMapper {

    private final ModelMapper modelMapper;

    public Salesman toModel(SalesmanNewDTO salesmanNewDTO) {
        return modelMapper.map(salesmanNewDTO, Salesman.class);
    }
}