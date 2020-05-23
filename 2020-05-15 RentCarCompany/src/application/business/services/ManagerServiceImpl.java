package application.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import application.business.dto.CarDto;
import application.business.dto.DtoService;
import application.business.dto.RentRecordDto;
import application.business.entities.Car;
import application.business.entities.CarState;
import application.business.entities.Model;
import application.business.entities.RentRecord;
import application.business.exceptions.RecordException;
import application.business.repositories.CarJpaRepository;
import application.business.repositories.ModelJpaRepository;
import application.business.repositories.RentRecordJpaRepository;

@Service
public class ManagerServiceImpl implements ManagerService{

	@Autowired RentRecordJpaRepository recordRepo;
	@Autowired CarJpaRepository carRepo;
	@Autowired ModelJpaRepository modelRepo;
	@Autowired DtoService dtoService;
	
	@Override
	@Transactional(readOnly = true)
	public List<RentRecordDto> getRecordsUnreceived() {
		return dtoService.collectionToList(recordRepo.findByReturnDateIsNull(), dtoService.recordDto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RentRecordDto> getRecordsUnclosed() {
		return dtoService.collectionToList(recordRepo.findByRecordClosedFalse(), dtoService.recordDto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CarDto> getAllCars() {
		return dtoService.collectionToList(carRepo.findAll(), dtoService.carDto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CarDto> getCarsInUse() {
		return dtoService.collectionToList(carRepo.findByStateEquals(CarState.IN_USE), dtoService.carDto);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CarDto> getCarsWrittenOff() {
		return dtoService.collectionToList(carRepo.findByStateEquals(CarState.WRITTEN_OFF), dtoService.carDto);
	}

	@Override
	@Transactional
	public CarDto addCar(CarDto car) {
		carRepo.checkById(car.getIdCar(), false);
		Model model = modelRepo.checkById(car.getIdModel(), true);
		Car carEntity = new Car(car.getIdCar(), model);
		carEntity.setState(CarState.AVAILABLE);
		carRepo.save(carEntity);
		return car;
	}

	@Override
	@Transactional
	public RentRecordDto closeRecord(int idRecord, double damagesRepairPrice) {
		RentRecord record = recordRepo.checkById(idRecord, true);
		if(record.isRecordClosed())
			throw new RecordException("The record " + idRecord + " is already closed");
		if(record.getReturnDate()== null)
			throw new RecordException("Car by record " + idRecord + " is not returned");
		record.setDamagesRepairPrice(damagesRepairPrice);
		record.setTotal(record.getTotal() + damagesRepairPrice);
		record.getCar().setState(CarState.AVAILABLE);
		record.setRecordClosed(true);
		return dtoService.getRentRecordDto(record);
	}

}
