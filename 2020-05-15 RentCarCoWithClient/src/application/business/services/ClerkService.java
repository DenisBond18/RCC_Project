package application.business.services;

import java.time.LocalDate;
import java.util.List;

import application.business.dto.CarDto;
import application.business.dto.DriverDto;
import application.business.dto.ModelDto;
import application.business.dto.RentRecordDto;

/*CLERK
- rentCar
- getModelsAvailable
- getCarsAvailable
- getCarsAvailableByModel
- getCar
- addDriver
- getDriver
- correctDriver
- getRecordsDelayedByDriver
- getRecordsByDriverInRentDateRange

- receiveCar
- setTankFull
- setFuelInTank
- setNoDamages

- getRecordTotal
*/
public interface ClerkService {

	DriverDto addDriver(DriverDto driverDto);
	DriverDto getDriver(String idDriver);
	DriverDto correctDriver(DriverDto driverDto);
	
	List<RentRecordDto> getRecordsDelayedByDriver(String idDriver);
	List<RentRecordDto> getRecordsByDriverInRentDateRange(String idDriver, LocalDate start, LocalDate finish);
	
	List<ModelDto> getModelsAvailable();
	
	List<CarDto> getCarsAvailable();
	List<CarDto> getCarsAvailableByModel(String idModel);
	CarDto getCar(String idCar);
	
	RentRecordDto getRecord(int idRecord);
	RentRecordDto rentCar(String idCar, String idDriver, LocalDate plannedReturnDate);
	RentRecordDto receiveCar(int idRecord, double fuelInTankPercent, boolean noDamages);
}
