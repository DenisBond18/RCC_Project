package application.business.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.business.dto.CarDto;
import application.business.dto.RentRecordDto;
import application.business.services.ManagerService;

@RestController
@CrossOrigin
@RequestMapping("/manager")
public class ManagerController {

	@Autowired ManagerService service;
	
	@GetMapping("/get_records_unreceived")
	public List<RentRecordDto> getRecordsUnreceived() {
		return service.getRecordsUnreceived();
	}

	@GetMapping("/get_records_unclosed")
	public List<RentRecordDto> getRecordsUnclosed() {
		return service.getRecordsUnclosed();
	}

	@GetMapping("/get_all_cars")
	public List<CarDto> getAllCars() {
		return service.getAllCars();
	}

	@GetMapping("/get_cars_in_use")
	public List<CarDto> getCarsInUse() {
		return service.getCarsInUse();
	}

	@GetMapping("/get_cars_written_off")
	public List<CarDto> getCarsWrittenOff() {
		return service.getCarsWrittenOff();
	}

	@PostMapping("/add_car")
	public CarDto addCar(@RequestBody @Valid CarDto car) {
		return service.addCar(car);
	}

	@PutMapping("/close_record")
	public RentRecordDto closeRecord(int idRecord, double damagesRepairPrice) {
		return service.closeRecord(idRecord, damagesRepairPrice);
	}
}
