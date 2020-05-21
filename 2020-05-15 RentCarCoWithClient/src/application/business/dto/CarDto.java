package application.business.dto;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CarDto {

	@Size(min = 3, max = 10, message = "The IDCar not valid")
	private String idCar;
	@Size(min = 1, max = 30, message = "The IDModel not valid")
	private String idModel;
	
}
