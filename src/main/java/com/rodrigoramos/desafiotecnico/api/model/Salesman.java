package com.rodrigoramos.desafiotecnico.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@ToString(exclude = {"id", "cpf", "salary"})
public class Salesman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String name;
    private Double salary;

    public Salesman() {
    }

    public Salesman(Long id, String cpf, String name, Double salary) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }
}
