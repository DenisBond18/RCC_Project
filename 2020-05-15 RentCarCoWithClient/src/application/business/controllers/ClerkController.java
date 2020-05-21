package application.business.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.business.dto.CarDto;
import application.business.dto.DriverDto;
import application.business.dto.ModelDto;
import application.business.dto.RentRecordDto;
import application.business.services.ClerkService;

@RestController
@CrossOrigin
@RequestMapping("/clerk")
@Validated
public class ClerkController {

	@Autowired ClerkService service;
	@Autowired DateTimeFormatter dateFormatter;
	
	@PostMapping("/add_driver")
	public DriverDto addDriver(@RequestBody @Valid DriverDto driverDto) {
		return service.addDriver(driverDto);
	}

	@GetMapping("/get_driver")
	public DriverDto getDriver(String idDriver) {
		return service.getDriver(idDriver);
	}

	@PostMapping("/correct_driver")
	public DriverDto correctDriver(@RequestBody @Valid DriverDto driverDto) {
		return service.correctDriver(driverDto);
	}

	@GetMapping("/get_records_delayed_by_driver")
	public List<RentRecordDto> getRecordsDelayedByDriver(String idDriver) {
		return service.getRecordsDelayedByDriver(idDriver);
	}

	@GetMapping("/get_records_by_driver_in_date_range")
	public List<RentRecordDto> getRecordsByDriverInRentDateRange(String idDriver,
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate start, 
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate finish) {
		return service.getRecordsByDriverInRentDateRange(idDriver, start, finish);
	}

	@GetMapping("/get_models_available")
	public List<ModelDto> getModelsAvailable() {
		return service.getModelsAvailable();
	}

	@GetMapping("/get_cars_available")
	public List<CarDto> getCarsAvailable() {
		return service.getCarsAvailable();
	}

	@GetMapping("/get_cars_available_by_model")
	public List<CarDto> getCarsAvailableByModel(String idModel) {
		return service.getCarsAvailableByModel(idModel);
	}

	@GetMapping("/get_car")
	public CarDto getCar(String idCar) {
		return service.getCar(idCar);
	}

	@GetMapping("/get_record")
	public RentRecordDto getRecord(int idRecord) {
		return service.getRecord(idRecord);
	}

	@PutMapping("/rent_car")
	public RentRecordDto rentCar(String idCar, String idDriver, 
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate plannedReturnDate) {
		return service.rentCar(idCar, idDriver, plannedReturnDate);
	}

	@PutMapping("/receive_car")
	public RentRecordDto receiveCar(int idRecord, double fuelInTankPercent, boolean noDamages) {
		return service.receiveCar(idRecord, fuelInTankPercent, noDamages);
	}
}
