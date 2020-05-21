package application.business.services;
/*MANAGER
- getRecordsUnreceived
- getRecordsUnclosed
- getAllCars
- getCarsInUse
- getCarsWrittenOff	
- addCar
- setDamagesRepairPrice
- closeRecord
+ CLERK*/

import java.util.List;

import application.business.dto.CarDto;
import application.business.dto.RentRecordDto;

public interface ManagerService {

	List<RentRecordDto> getRecordsUnreceived();
	List<RentRecordDto> getRecordsUnclosed();
	List<CarDto> getAllCars();
	List<CarDto> getCarsInUse();
	List<CarDto> getCarsWrittenOff();
	CarDto addCar(CarDto car);
	RentRecordDto closeRecord(int idRecord, double damagesRepairPrice);
	
}
