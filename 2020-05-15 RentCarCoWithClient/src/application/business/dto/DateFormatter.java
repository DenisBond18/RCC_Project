package application.business.dto;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import application.business.exceptions.WrongDateTimePatternException;

@Configuration
public class DateFormatter {

	@Value("${rcc.dateformat}")
	private String pattern;
	
	@Bean
	public DateTimeFormatter getdateTimeFormatter() {
		try {
			return DateTimeFormatter.ofPattern(pattern);
		} catch (Exception e) {
			throw new WrongDateTimePatternException("Wrong pattern " + pattern);
		}
		
		
	}
}
