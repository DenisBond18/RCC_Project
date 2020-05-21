package application.business.dto;

import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class RentRecordDto {

	private int idRecord;
	private String idDriver;
	private String idCar;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String rentDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String rentDurationDays;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private String returnDate;
	@Positive
	private double fuelInTankPercent;
	@Positive
	private double damagesRepairPrice;
	@Positive
	private double total;
	private boolean recordClosed;
}
