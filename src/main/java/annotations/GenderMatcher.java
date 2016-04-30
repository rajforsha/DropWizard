package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import validations.GenderValidation;

/**
 * @author shashi
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValidation.class)
public @interface GenderMatcher {

	String message() default "gender match error!!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
