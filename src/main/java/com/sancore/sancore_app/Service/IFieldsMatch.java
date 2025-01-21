package com.sancore.sancore_app.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.sancore.sancore_app.Service.Impl.FieldsMatchValidatorImpl;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.TYPE }) // Se aplica a las clases
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldsMatchValidatorImpl.class)
@Repeatable(IFieldsMatches.class) // Esto permite que se repita
public @interface IFieldsMatch {

    String message() default "Los campos no coinciden"; // Mensaje predeterminado

    String field(); // Primer campo a comparar

    String fieldMatch(); // Segundo campo a comparar

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
