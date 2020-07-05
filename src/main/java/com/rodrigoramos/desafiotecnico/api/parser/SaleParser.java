package com.rodrigoramos.desafiotecnico.api.parser;


import com.rodrigoramos.desafiotecnico.api.dto.SaleNewDTO;
import com.rodrigoramos.desafiotecnico.api.model.SaleItem;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SaleParser {

    private static final String ARRAY_DELIMITER = ",";

    public static SaleNewDTO parse(StringTokenizer tokenizer) {
        SaleNewDTO sale = new SaleNewDTO();
        sale.setSaleId(Long.parseLong(tokenizer.nextToken()));
        List<SaleItem> saleItems = getSaleItems(tokenizer.nextToken());
        sale.setSaleItems(saleItems);
        sale.setSalesmanName(tokenizer.nextToken());

        return sale;
    }

    private static List<SaleItem> getSaleItems(String value) {
        value = StringUtils.substringBetween(value, "[", "]");
        List<SaleItem> items = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(value, ARRAY_DELIMITER);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            SaleItem saleItem = SaleItemParser.parse(token);
            items.add(saleItem);
        }
        return items;
    }

    private SaleParser() {
    }
}
