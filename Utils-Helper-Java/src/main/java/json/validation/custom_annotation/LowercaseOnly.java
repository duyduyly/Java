package json.validation.custom_annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LowercaseValidator.class) // link to validator
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface LowercaseOnly {

    String message() default "must be lowercase letters only";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}