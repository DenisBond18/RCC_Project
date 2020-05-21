package application.business.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.business.dto.CarDto;
import application.business.dto.CarNumber;
import application.business.dto.DriverNumber;
import application.business.dto.ModelDto;
import application.business.dto.RentRecordDto;
import application.business.services.AnalistService;

@RestController
@CrossOrigin
@RequestMapping("/analist")
public class AnalistController {

	@Autowired AnalistService service;
	
	@GetMapping("/get_all_cars")
	List<CarDto> getAllCars(){
		return service.getAllCars();
	}
	@GetMapping("/get_cars_by_model")
	List<CarDto> getCarsByModel(String idModel){
		return service.getCarsByModel(idModel);
	}
	@GetMapping("/get_records_in_rent_day_range")
	List<RentRecordDto> getRecordsInRentDateRange(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate from,
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate to){
		return service.getRecordsInRentDateRange(from, to);
	}
	@GetMapping("/get_most_sloopy_drivers")
	List<DriverNumber> getMostSloopyDrivers(){
		return service.getMostSloopyDrivers();
	}
	@GetMapping("/get_income_in_date_range")
	double findIncomeInDateRange(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate from, 
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate to) {
		return service.findIncomeInDateRange(from, to);
	}
	@GetMapping("/get_records_delayed")
	List<RentRecordDto> getRecordsDelayedInRentDateRange(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate from, 
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate to){
		return service.getRecordsDelayedInRentDateRange(from, to);
	}
	@GetMapping("/get_all_models")
	List<ModelDto> getAllModels(){
		return service.getAllModels();
	}
	@GetMapping("/get_models_empty")
	List<ModelDto> getModelsEmpty(){
		return service.getModelsEmpty();
	}
	@GetMapping("/get_records_by_model")
	List<RentRecordDto> getRecordsByModelInDateRange(String idModel, 
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate from, 
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate to){
		return service.getRecordsByModelInDateRange(idModel, from, to);
	}
	@GetMapping("/get_total_in_date_range")
	double getTotalInDateRange(@RequestParam @DateTimeFormat(iso = ISO.DATE)LocalDate from,
								@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate to) {
		return service.getTotalInDateRange(from, to);
	}
	@GetMapping("/get_most_pay_driver")
	List<DriverNumber> getMostPayDrivers(){
		return service.getMostPayDrivers();
	}
	@GetMapping("/get_records_by_car_in_date-range")
	List<RentRecordDto> getRecordsByCarInDateRange(String idCar, 
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate from, 
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate to){
		return service.getRecordsByCarInDateRange(idCar, from, to);
	}
	@GetMapping("/get_total_by_car")
	double getTotalByCarInDateRange(String idCar, 
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate from, 
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate to) {
		return service.getTotalByCarInDateRange(idCar, from, to);
	}
	@GetMapping("/get_total_by_model")
	double getTotalByModelInDateRange(String idModel, 
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate from, 
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate to) {
		return service.getTotalByModelInDateRange(idModel, from, to);
	}
	@GetMapping("/get_incom_by_car")
	double getIncomeByCarInDateRange(String idCar, 
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate from, 
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate to) {
		return service.getIncomeByCarInDateRange(idCar, from, to);
	}
	@GetMapping("/get_most_used_cars")
	List<CarNumber> getMostUsedCars(){
		return service.getMostUsedCars();
	}
	@GetMapping("/get_total_damages_repair_price")
	double getTotalDamagesRepairPriceInDateRange(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate from, 
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate to) {
		return service.getTotalDamagesRepairPriceInDateRange(from, to);
	}
}
