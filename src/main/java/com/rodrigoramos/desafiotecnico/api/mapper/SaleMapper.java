package com.rodrigoramos.desafiotecnico.api.mapper;

import com.rodrigoramos.desafiotecnico.api.dto.SaleNewDTO;
import com.rodrigoramos.desafiotecnico.api.model.Sale;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaleMapper {

    private final ModelMapper modelMapper;

    public Sale toModel(SaleNewDTO saleNewDTO) {
        return modelMapper.map(saleNewDTO, Sale.class);
    }
}