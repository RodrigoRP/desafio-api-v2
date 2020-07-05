package com.rodrigoramos.desafiotecnico.api.service.validation;

import com.rodrigoramos.desafiotecnico.api.controller.exceptions.FieldMessage;
import com.rodrigoramos.desafiotecnico.api.dto.CustomerNewDTO;
import com.rodrigoramos.desafiotecnico.api.service.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsert, CustomerNewDTO> {
    @Override
    public void initialize(CustomerInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(CustomerNewDTO customerNewDTO, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (!BR.isValidCNPJ(customerNewDTO.getCnpj())) {
            list.add(new FieldMessage("cnpj", "Invalid CNPJ!"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
