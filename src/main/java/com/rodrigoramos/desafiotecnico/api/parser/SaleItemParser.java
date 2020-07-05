package com.rodrigoramos.desafiotecnico.api.parser;

import com.rodrigoramos.desafiotecnico.api.model.SaleItem;

import java.util.StringTokenizer;

public class SaleItemParser {

    private static final String VALUES_DELIMITER = "-";

    public static SaleItem parse(String value) {
        StringTokenizer tokenizer = new StringTokenizer(value, VALUES_DELIMITER);

        SaleItem saleItem = new SaleItem();
        saleItem.setItemId(Integer.parseInt(tokenizer.nextToken()));
        saleItem.setQuantity(Integer.parseInt(tokenizer.nextToken()));
        saleItem.setPrice(Double.parseDouble(tokenizer.nextToken()));

        return saleItem;

    }

    private SaleItemParser() {
    }
}
