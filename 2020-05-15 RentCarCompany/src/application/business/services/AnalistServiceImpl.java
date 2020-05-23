package application.business.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.business.dto.CarDto;
import application.business.dto.CarNumber;
import application.business.dto.DriverNumber;
import application.business.dto.DtoService;
import application.business.dto.ModelDto;
import application.business.dto.RentRecordDto;
import application.business.entities.Car;
import application.business.entities.Model;
import application.business.repositories.CarJpaRepository;
import application.business.repositories.ModelJpaRepository;
import application.business.repositories.RentRecordJpaRepository;
/*	
+ getRecordsInRentDateRange
+ getRecordsDelayedInRentDateRange
+ getRecordsUnreceived - implemented in the ManagerService
+ getRecordsUnclosed - implemented in the ManagerService

+ getAllModels
+ getModelsAvailable - implemented in the ClerkService
+ getModelsEmpty
+ getRecordsByModelInDateRange
+ getTotalByModelInDateRange
- getIncomeByModelInDateRange
- getMostUsedModels
- getMostSaledModels

+ getAllCars
+ getCarsByModel
+ getRecordsByCarInDateRange
+ getTotalByCarInDateRange
+ getIncomeByCarInDateRange
+ getMostUsedCars

- getRecordsDelayedInDateRange
- getRecordsDamagedInDateRange
+ getMostPayDrivers
+ getMostSloppyDrivers

+ getTotalInDateRange
+ getIncomeInDateRange
- getTotalDelayInDateRange
- getTotalFuelFeesInDateRange
+ getTotalDamagesRepairPriceInDateRange
*/
@Service
public class AnalistServiceImpl implements AnalistService{

	@Autowired RentRecordJpaRepository recordRepo;
	@Autowired CarJpaRepository carRepo;
	@Autowired ModelJpaRepository modelRepo;
	
	@Autowired DtoService dtoService;
	
	@Override
	public List<DriverNumber> getMostSloopyDrivers() {
		return recordRepo.findMostSloopyDrivers();
	}

	@Override
	public double findIncomeInDateRange(LocalDate from, LocalDate to) {
		return recordRepo.findIncomInDateRange(from, to);
	}

	@Override
	public List<RentRecordDto> getRecordsInRentDateRange(LocalDate from, LocalDate to) {
		return dtoService.collectionToList(recordRepo.findByRentDateBetween(from, to), dtoService.recordDto);
	}

	@Override
	public List<RentRecordDto> getRecordsDelayedInRentDateRange(LocalDate from, LocalDate to) {
		return dtoService.collectionToList(recordRepo.findRecordsDelayedInRentDateRange(from, to), dtoService.recordDto);
	}

	@Override
	public List<ModelDto> getAllModels() {
		return dtoService.collectionToList(modelRepo.findAll(), dtoService.modelDto);
	}

	@Override
	public List<ModelDto> getModelsEmpty() {
		return dtoService.collectionToList(modelRepo.findModelsEmpty(), dtoService.modelDto);
	}

	@Override
	public List<RentRecordDto> getRecordsByModelInDateRange(String idModel, LocalDate from, LocalDate to) {
		Model model = modelRepo.checkById(idModel, true);
		return dtoService.collectionToList(recordRepo.findByCarModelAndRentDateBetween(model, from, to), dtoService.recordDto);
	}

	@Override
	public double getTotalInDateRange(LocalDate from, LocalDate to) {
		return recordRepo.getTotal(from, to);
	}

	@Override
	public List<DriverNumber> getMostPayDrivers() {
		return null;//return recordRepo.findMostPayDrivers();
	}

	@Override
	public List<RentRecordDto> getRecordsByCarInDateRange(String idCar, LocalDate from, LocalDate to) {
		Car car = carRepo.checkById(idCar, true);
		return dtoService.collectionToList(recordRepo.findByCarAndRentDateBetween(car, from, to), dtoService.recordDto);
	}

	@Override
	public double getTotalByCarInDateRange(String idCar, LocalDate from, LocalDate to) {
		Car car = carRepo.checkById(idCar, true);
		return recordRepo.findTotalByCarInDateRange(car, from, to);
	}

	@Override
	public List<CarDto> getAllCars() {
		return dtoService.collectionToList(carRepo.findAll(), dtoService.carDto);
	}

	@Override
	public List<CarDto> getCarsByModel(String idModel) {
		Model model = modelRepo.checkById(idModel, true);
		return dtoService.collectionToList(carRepo.findByModelEquals(model), dtoService.carDto);
	}

	@Override
	public double getTotalByModelInDateRange(String idModel, LocalDate from, LocalDate to) {
		return recordRepo.findTotalByModelInDateRange(idModel, from, to);
	}

	@Override
	public double getIncomeByCarInDateRange(String idCar, LocalDate from, LocalDate to) {
		return recordRepo.findIncomeByCarInDateRange(idCar, from, to);
	}

	@Override
	public List<CarNumber> getMostUsedCars() {
		return recordRepo.findMostUsedCars();
	}

	@Override
	public double getTotalDamagesRepairPriceInDateRange(LocalDate from, LocalDate to) {
		return recordRepo.findTotalDamagesRepairPriceInDateRange(from, to);
	}

	

	
}
