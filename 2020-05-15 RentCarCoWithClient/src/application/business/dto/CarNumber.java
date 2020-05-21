package application.business.dto;

import application.business.entities.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CarNumber {

	private Car car;
	private Number value;
}
