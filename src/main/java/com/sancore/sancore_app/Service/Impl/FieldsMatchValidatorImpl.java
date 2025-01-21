package com.sancore.sancore_app.Service.Impl;

import org.apache.commons.beanutils.BeanUtils;
import com.sancore.sancore_app.Service.IFieldsMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FieldsMatchValidatorImpl implements ConstraintValidator<IFieldsMatch, Object> {

    private String field;
    private String fieldMatch;

    @Override
    public void initialize(IFieldsMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            final Object fieldValue = BeanUtils.getProperty(value, field);
            final Object fieldMatchValue = BeanUtils.getProperty(value, fieldMatch);

            // Verifica que los dos campos no sean nulos y que coincidan
            return fieldValue != null && fieldValue.equals(fieldMatchValue);
        } catch (Exception ignore) {
            // Si ocurre un error (por ejemplo, propiedades nulas), se puede considerar
            // válido
        }
        return true;
    }
}
