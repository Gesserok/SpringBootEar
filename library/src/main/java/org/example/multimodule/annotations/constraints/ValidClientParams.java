package org.example.multimodule.annotations.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ClientParamsValidator.class)
@Documented
public @interface ValidClientParams {

    String message() default "{ClientParams.invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
