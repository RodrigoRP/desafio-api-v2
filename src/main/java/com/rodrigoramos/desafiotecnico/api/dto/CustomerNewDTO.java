package com.rodrigoramos.desafiotecnico.api.dto;

import com.rodrigoramos.desafiotecnico.api.service.validation.CustomerInsert;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@CustomerInsert
public class CustomerNewDTO {

    @NotEmpty(message = "Required Field")
    private String cnpj;

    @NotEmpty(message = "Required Field")
    @Length(min = 2, max = 100, message = "The length must be between 2 and 100 characters")
    private String name;

    @NotEmpty(message = "Required Field")
    @Length(min = 5, max = 100, message = "The length must be between 5 and 100 characters")
    private String businessArea;

    public CustomerNewDTO() {
    }

    public CustomerNewDTO(String cnpj, String name, String businessArea) {
        this.cnpj = cnpj;
        this.name = name;
        this.businessArea = businessArea;
    }

}
