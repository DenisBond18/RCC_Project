package application.business.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import application.business.config.RccConfig;
import application.business.dto.CarDto;
import application.business.dto.DriverDto;
import application.business.dto.DtoService;
import application.business.dto.ModelDto;
import application.business.dto.RentRecordDto;
import application.business.entities.Car;
import application.business.entities.CarState;
import application.business.entities.Driver;
import application.business.entities.Model;
import application.business.entities.RentRecord;
import application.business.exceptions.ConfigurationException;
import application.business.exceptions.RecordException;
import application.business.repositories.CarJpaRepository;
import application.business.repositories.DriverJpaRepository;
import application.business.repositories.ModelJpaRepository;
import application.business.repositories.RccConfigJpaRepository;
import application.business.repositories.RentRecordJpaRepository;

@Service
public class ClerkServiceImpl implements ClerkService{

	@Autowired DriverJpaRepository driverRepo;
	@Autowired RentRecordJpaRepository recordRepo;
	@Autowired ModelJpaRepository modelRepo;
	@Autowired CarJpaRepository carRepo;
	@Autowired RccConfigJpaRepository configRepo;
	
	@Autowired DtoService dtoService;
	
	@Override
	@Transactional
	public DriverDto addDriver(DriverDto driverDto) {
		driverRepo.checkById(driverDto.getIdDriver(), false);
		Driver driver = dtoService.getDriver(driverDto);
		driverRepo.save(driver);
		return driverDto;
	}

	@Override
	@Transactional(readOnly = true)
	public DriverDto getDriver(String idDriver) {
		Driver driver = driverRepo.checkById(idDriver, true);
		return dtoService.getDriverDto(driver);
	}

	@Override
	@Transactional
	public DriverDto correctDriver(DriverDto driverDto) {
		driverRepo.checkById(driverDto.getIdDriver(), true);
		Driver driver = dtoService.getDriver(driverDto);
		driverRepo.save(driver);
		return driverDto;
	}

	@Override
	@Transactional(readOnly = true)
	public List<RentRecordDto> getRecordsDelayedByDriver(String idDriver) {
		return dtoService.collectionToList(recordRepo.findByDriverDelayed(idDriver), dtoService.recordDto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RentRecordDto> getRecordsByDriverInRentDateRange(String idDriver, LocalDate start, LocalDate finish) {
		return dtoService.collectionToList(recordRepo.findRecordsByDriverInRentDateRange(idDriver, start, finish), dtoService.recordDto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ModelDto> getModelsAvailable() {
		return dtoService.collectionToList(modelRepo.findModelsAvailable(), dtoService.modelDto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CarDto> getCarsAvailable() {
		return dtoService.collectionToList(carRepo.findByStateEquals(CarState.AVAILABLE), dtoService.carDto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CarDto> getCarsAvailableByModel(String idModel) {
		return dtoService.collectionToList(carRepo.findCarsAvailableByModel(idModel), dtoService.carDto);
	}

	@Override
	@Transactional(readOnly = true)
	public CarDto getCar(String idCar) {
		Car car = carRepo.checkById(idCar, true);
		return dtoService.getCarDto(car);
	}

	@Override
	@Transactional(readOnly = true)
	public RentRecordDto getRecord(int idRecord) {
		RentRecord record = recordRepo.checkById(idRecord, true);
		return dtoService.getRentRecordDto(record);
	}

	@Override
	@Transactional
	public RentRecordDto rentCar(String idCar, String idDriver, LocalDate plannedReturnDate) {
		Car car = carRepo.checkById(idCar, true);
		Driver driver = driverRepo.checkById(idDriver, true);
		LocalDate now = LocalDate.now();
		if(plannedReturnDate.isBefore(now))
			throw new RecordException("Planned return date must " + plannedReturnDate + " precedes the current one");
		if(!car.getState().equals(CarState.AVAILABLE))
			throw new RecordException("This car " + idCar + " is not available");
		RentRecord record = new RentRecord(driver, car, now, plannedReturnDate);
		car.setState(CarState.IN_USE);
		carRepo.save(car);
		recordRepo.save(record);	
		return dtoService.getRentRecordDto(record);
	}

	
	@Override
	@Transactional
	public RentRecordDto receiveCar(int idRecord, double fuelInTankPercent, boolean noDamages) {
		RentRecord record = recordRepo.checkById(idRecord, true);
		record.setReturnDate(LocalDate.now());
		record.setFuelInTankPercent(fuelInTankPercent);
		record.setTotal(computeTotal(record));
		if (noDamages) {
			record.setDamagesRepairPrice(0.);
			record.setRecordClosed(true);
			record.getCar().setState(CarState.AVAILABLE);
		}
		else {
			record.getCar().setState(CarState.OUT_OF_SERVICE);
		}
		return dtoService.getRentRecordDto(record);
	}

	private double computeTotal(RentRecord record) {
		RccConfig config = configRepo.findById(1).orElse(null);
		if (config == null) {
			throw new ConfigurationException("Business configuration not set");
		}
		
		Model model = record.getCar().getModel();
		int rentDays = Period.between(record.getPlannedReturnDate(), record.getRentDate()).getDays();
		int delayDays = Integer.max(0, Period.between(record.getReturnDate(), record.getPlannedReturnDate()).getDays());
		
		return rentDays*model.getDailyRate() + delayDays*config.getDelayPenalty() + 
				config.getFuelPrice()*model.getTankVolume()*(1. - record.getFuelInTankPercent()/100.);
	}

}
