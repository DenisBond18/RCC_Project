package application.business.services;

import java.time.LocalDate;
import java.util.List;

import application.business.dto.CarDto;
import application.business.dto.CarNumber;
import application.business.dto.DriverNumber;
import application.business.dto.ModelDto;
import application.business.dto.RentRecordDto;

public interface AnalistService {
	
	List<CarDto> getAllCars();
	List<CarDto> getCarsByModel(String idModel);
	List<RentRecordDto> getRecordsInRentDateRange(LocalDate from, LocalDate to);
	List<DriverNumber> getMostSloopyDrivers();
	double findIncomeInDateRange(LocalDate from, LocalDate to);
	List<RentRecordDto> getRecordsDelayedInRentDateRange(LocalDate from, LocalDate to);
	List<ModelDto> getAllModels();
	List<ModelDto> getModelsEmpty();
	List<RentRecordDto> getRecordsByModelInDateRange(String idModel, LocalDate from, LocalDate to);
	double getTotalInDateRange(LocalDate from, LocalDate to);
	List<DriverNumber> getMostPayDrivers();
	List<RentRecordDto> getRecordsByCarInDateRange(String idCar, LocalDate from, LocalDate to);
	double getTotalByCarInDateRange(String idCar, LocalDate from, LocalDate to);
	double getTotalByModelInDateRange(String idModel, LocalDate from, LocalDate to);
	double getIncomeByCarInDateRange(String idCar, LocalDate from, LocalDate to);
	List<CarNumber> getMostUsedCars();
	double getTotalDamagesRepairPriceInDateRange(LocalDate from, LocalDate to);
}
