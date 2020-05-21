package application.business.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import application.business.exceptions.ConfigurationException;
import application.business.exceptions.RecordException;
import application.common.DuplicatedIdException;
import application.common.IdNotFoundException;

@ControllerAdvice //(basePackages = { "application.business.controllers" })
public class ExceptionHandlers {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<String>> handleValidationErrors(MethodArgumentNotValidException exception) {
		List<String> result = new ArrayList<>();

		BindingResult bindingResult = exception.getBindingResult();
		result.addAll(bindingResult.getAllErrors().stream().map(e -> {
			String rejectedValue = "";
			try {
				rejectedValue = ": " + ((FieldError) e).getRejectedValue();
			} catch (Exception exc) {
			}
			return e.getDefaultMessage() + rejectedValue;
		}).collect(Collectors.toList()));
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List<String>> handleConstraintErrors(ConstraintViolationException exception) {
		List<String> result = exception.getConstraintViolations().stream()
				.map(e -> e.getMessage() + ": " + e.getInvalidValue()).collect(Collectors.toList());
		return new ResponseEntity<List<String>>(result, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handleJsonErrors(HttpMessageNotReadableException exception) {
		return new ResponseEntity<>("User request error! " + exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleOtherErrors(Exception exception) {
		exception.printStackTrace();
		return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = {IdNotFoundException.class, 
            					DuplicatedIdException.class,
            					ConfigurationException.class,
            					RecordException.class}) 
	public ResponseEntity<?> handleIdNotFound(RuntimeException runTimeException) {
		runTimeException.printStackTrace();
		return new ResponseEntity<>(runTimeException.getMessage(), HttpStatus.BAD_REQUEST);
}
}
