package application.business.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import application.business.annotations.DateFormat;

public class DateFormatValidator implements ConstraintValidator<DateFormat, String>{

	private String format;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(value);
			return true;
		} catch (ParseException e) {
			return false;
		}	
	}

	@Override
	public void initialize(DateFormat constraintAnnotation) {
		format = constraintAnnotation.format();
	}

	
}
