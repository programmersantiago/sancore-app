package com.sancore.sancore_app.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Payload;

@Target({ ElementType.TYPE }) // Se aplica a las clases
@Retention(RetentionPolicy.RUNTIME)
public @interface IFieldsMatches {

    String message() default "Los campos no coinciden"; // Mensaje predeterminado

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    IFieldsMatch[] value(); // Aquí van las anotaciones @IFieldsMatch
}
