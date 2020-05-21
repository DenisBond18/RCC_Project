package application.business.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import application.business.validators.DateFormatValidator;

@Constraint(validatedBy = DateFormatValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DateFormat {

	String format();
	String message() default "{Format}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
