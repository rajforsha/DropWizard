/**
 * 
 */
package validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import annotations.GenderMatcher;

/**
 * @author shashi
 *
 */
public class GenderValidation implements ConstraintValidator<GenderMatcher, Object> {

	public void initialize(GenderMatcher arg0) {
	}

	public boolean isValid(Object value, ConstraintValidatorContext arg1) {
		if ("MALE".equalsIgnoreCase(value.toString()) || "FEMALE".equalsIgnoreCase(value.toString())) {
			return true;
		} else
			return false;
	}

}
