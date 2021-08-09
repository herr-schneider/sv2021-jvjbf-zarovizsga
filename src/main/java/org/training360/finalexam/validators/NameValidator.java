package  org.training360.finalexam.validators;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {

    int minLength;
    int maxLength;
    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        if (name == null || name.isBlank()){return false;}
        return name.trim().length()>minLength && name.length()<maxLength &&
                Character.isUpperCase(name.trim().charAt(0)); // && person.getName().trim().contains(" ");
        }

    @Override
    public void initialize(Name constraintAnnotation) {
        minLength = constraintAnnotation.minLength();
        maxLength = constraintAnnotation.maxLength();
    }
}
