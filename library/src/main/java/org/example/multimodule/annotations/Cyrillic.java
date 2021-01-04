package org.example.multimodule.annotations;

import javax.validation.constraints.NotBlank;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cyrillic {
    @NotBlank String source();
}
