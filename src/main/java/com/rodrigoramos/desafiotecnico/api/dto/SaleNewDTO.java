package com.rodrigoramos.desafiotecnico.api.dto;

import com.rodrigoramos.desafiotecnico.api.model.SaleItem;
import com.rodrigoramos.desafiotecnico.api.service.validation.SaleInsert;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SaleInsert
public class SaleNewDTO {

    private Long saleId;
    private List<SaleItem> saleItems = new ArrayList<>();
    private String salesmanName;

    public SaleNewDTO() {
    }

    public SaleNewDTO(Long saleId, List<SaleItem> saleItems, String salesmanName) {
        this.saleId = saleId;
        this.saleItems = saleItems;
        this.salesmanName = salesmanName;
    }

}
