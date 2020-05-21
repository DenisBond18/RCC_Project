package application.business.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModelDto {

	@Size(min = 2, max = 30, message = "This IdModel is not valid")
	private String idModel;
	
	@Positive(message = "DailyRate must be positive or 0")
	private double dailyRate = 0.;
	@Positive(message = "TankVolume must be positive or 0")
	private double tankVolume = 0.;
}
