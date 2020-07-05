package com.rodrigoramos.desafiotecnico.api.service.validation;

import com.rodrigoramos.desafiotecnico.api.controller.exceptions.FieldMessage;
import com.rodrigoramos.desafiotecnico.api.dto.SaleNewDTO;
import com.rodrigoramos.desafiotecnico.api.model.Salesman;
import com.rodrigoramos.desafiotecnico.api.repository.SalesmanRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class SaleInsertValidator implements ConstraintValidator<SaleInsert, SaleNewDTO> {

    @Autowired
    private SalesmanRepository salesmanRepository;

    @Override
    public void initialize(SaleInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(SaleNewDTO saleNewDTO, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        Salesman salesman = salesmanRepository.findByName(saleNewDTO.getSalesmanName());
        if (salesman == null) {
            list.add(new FieldMessage("salesmanName", "Salesman not registered"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
