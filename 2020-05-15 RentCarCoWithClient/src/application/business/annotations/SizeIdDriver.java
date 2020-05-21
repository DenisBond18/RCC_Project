package application.business.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import application.business.validators.SizeIdDriverValidator;

@Constraint(validatedBy = SizeIdDriverValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SizeIdDriver {
	
	int sizeIdDriver();
	String message() default "{SizeIdDriver}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
