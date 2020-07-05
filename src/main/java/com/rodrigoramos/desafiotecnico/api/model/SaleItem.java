package com.rodrigoramos.desafiotecnico.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class SaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private Integer itemId;
    private Integer quantity;
    private Double price;

    public SaleItem() {
    }

    public SaleItem(Long id, Integer itemId, Integer quantity, Double price) {
        this.id = id;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
    }


}
