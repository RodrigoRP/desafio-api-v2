package com.rodrigoramos.desafiotecnico.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class Sale {

    @Id
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<SaleItem> saleItems = new ArrayList<>();

    @ManyToOne
    private Salesman salesmanName;

    public Sale() {
    }

    public Sale(Long id, List<SaleItem> saleItems, Salesman salesmanName) {
        this.id = id;
        this.saleItems = saleItems;
        this.salesmanName = salesmanName;
    }

    public Double getValue() {
        return saleItems.stream()
                .mapToDouble(SaleItem::getPrice)
                .sum();
    }
}
