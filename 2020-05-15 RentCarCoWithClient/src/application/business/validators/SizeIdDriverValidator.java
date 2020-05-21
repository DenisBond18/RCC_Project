package application.business.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import application.business.annotations.SizeIdDriver;

public class SizeIdDriverValidator implements ConstraintValidator<SizeIdDriver, String> {

	private int sizeIdDriver;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		int length = value.length();
		return sizeIdDriver == length;
	}

	@Override
	public void initialize(SizeIdDriver annotation) {
		this.sizeIdDriver = annotation.sizeIdDriver();
	}

}
