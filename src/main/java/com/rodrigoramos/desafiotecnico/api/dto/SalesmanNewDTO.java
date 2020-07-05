package com.rodrigoramos.desafiotecnico.api.dto;

import com.rodrigoramos.desafiotecnico.api.service.validation.SalesmanInsert;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@SalesmanInsert
public class SalesmanNewDTO {

    @NotEmpty(message = "Required Field")
    private String cpf;

    @NotEmpty(message = "Required Field")
    @Length(min = 2, max = 100, message = "The length must be between 2 and 100 characters")
    private String name;

    private Double salary;

    public SalesmanNewDTO() {
    }

    public SalesmanNewDTO(String cpf, String name, Double salary) {
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }

}
