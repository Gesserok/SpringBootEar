package org.example.multimodule.annotations.constraints;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProxyParamsValidator.class)
@Documented
public @interface ValidProxyParams {
    String message() default "{ProxyParams.invalid}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
