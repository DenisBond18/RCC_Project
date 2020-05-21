package application.business.dto;

import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;

import application.business.annotations.DateFormat;
import application.business.annotations.SizeIdDriver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DriverDto {

	@SizeIdDriver(sizeIdDriver = 6, message = "IdDriver must be only 6 characters")
	private String idDriver;
	@Pattern(regexp = "[a-zA-z]*")
	private String firstName;
	@Pattern(regexp = "[a-zA-z]*")
	private String lastName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@DateFormat(format = "yyyy-MM-dd", message = "The date must be in this format: [yyyy-MM-dd]")
	private String birthDate;
	private String phoneNumber;
	private String email;
}
