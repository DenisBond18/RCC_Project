package application.business.dto;

import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class RccConfigDto {

	@Positive(message = "DelayPenalty must be positive or 0")
	private double delayPenalty;
	@Positive(message = "FuelPice must be positive or 0")
	private double fuelPrice;
}
