package application.business.dto;

import application.business.entities.Driver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverNumber {

	private Driver driver;
	private Number value;
}
