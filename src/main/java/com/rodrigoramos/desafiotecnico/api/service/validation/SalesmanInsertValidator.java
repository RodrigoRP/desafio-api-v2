package com.rodrigoramos.desafiotecnico.api.service.validation;

import com.rodrigoramos.desafiotecnico.api.controller.exceptions.FieldMessage;
import com.rodrigoramos.desafiotecnico.api.dto.SalesmanNewDTO;
import com.rodrigoramos.desafiotecnico.api.service.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class SalesmanInsertValidator implements ConstraintValidator<SalesmanInsert, SalesmanNewDTO> {
    @Override
    public void initialize(SalesmanInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(SalesmanNewDTO salesmanNewDTO, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (!BR.isValidCPF(salesmanNewDTO.getCpf())) {
            list.add(new FieldMessage("cpf", "Invalid CPF!"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
