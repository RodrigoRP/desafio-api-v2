package com.rodrigoramos.desafiotecnico.api.model.enums;

public enum Identifier {

    SALESMAN(1, "ROLE_SALESMAN"),
    CUSTOMER(2, "ROLE_CUSTOMER"),
    SALE(3, "ROLE_SALE");


    private int cod;
    private String description;

    private Identifier(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription () {
        return description;
    }

    public static Identifier toEnum(Integer cod) {

        if (cod == null) {
            return null;
        }

        for (Identifier x : Identifier.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }
}
