package json.validation.custom_annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LowercaseValidator implements ConstraintValidator<LowercaseOnly, String> {

    @Override
    public void initialize(LowercaseOnly constraintAnnotation) {
        // you can read annotation parameters here if needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true; // treat null as valid (use @NotNull if needed)
        return value.matches("[a-z]+");
    }
}
