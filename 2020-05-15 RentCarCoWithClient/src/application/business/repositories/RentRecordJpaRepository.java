package application.business.repositories;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.Query;

import application.business.dto.CarNumber;
import application.business.dto.DriverNumber;
import application.business.entities.Car;
import application.business.entities.Model;
import application.business.entities.RentRecord;
import application.common.CustomizedJpaRepository;

interface CustomizedRentRecordJpaRepository{
	List<DriverNumber> findMostPayDrivers();
}

class CustomizedRentRecordJpaRepositoryImpl implements CustomizedRentRecordJpaRepository{
	@PersistenceContext
	EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DriverNumber> findMostPayDrivers() {
		String strSubQuery = "SELECT SUM(r.total) FROM RentRecord r GROUP BY r.driver ORDER BY SUM(r.total) DESC";
		long maxPay = (long) entityManager.createQuery(strSubQuery).setMaxResults(1).getSingleResult(); 
		
		String strQuery = "SELECT DISTINCT(r.driver), SUM(r.total) FROM RentRecord r WHERE r.driver IN "
				+ "(SELECT rr.driver FROM RentRecord rr GROUP BY rr.driver HAVING SUM(rr.total) = :maxPay)";
		return entityManager.createQuery(strQuery).setParameter("maxPay", maxPay).getResultList();
	}
	
}
public interface RentRecordJpaRepository extends CustomizedJpaRepository<RentRecord, Integer>, CustomizedRentRecordJpaRepository{

	@Override
	default String type() {return "Record";}
	
	List<RentRecord> findByReturnDateIsNull();
	List<RentRecord> findByRecordClosedFalse();
	List<RentRecord> findByRentDateBetween(LocalDate from, LocalDate to);
	List<RentRecord> findByCarAndRentDateBetween(Car car, LocalDate from, LocalDate to);
	List<RentRecord> findByCarModelAndRentDateBetween(Model model, LocalDate from, LocalDate to);
	
	@Query("SELECT r FROM RentRecord r WHERE NOT(r.returnDate = NULL) AND r.plannedReturnDate < r.returnDate AND r.driver.idDriver = ?1")
	List<RentRecord> findByDriverDelayed(String idDriver);
	
	@Query("SELECT r FROM RentRecord r WHERE r.driver.idDriver = ?1 AND (r.rentDate BETWEEN ?2 AND ?3)")
	List<RentRecord> findRecordsByDriverInRentDateRange(String idDriver, LocalDate start, LocalDate finish);
	
	@Query("SELECT COALESCE(SUM(r.total), 0) FROM RentRecord r WHERE r.recordClosed=true AND r.driver.idDriver=?1 AND (r.rentDate BETWEEN ?2 AND ?3)")
	double findTotalByDriverInDateRange(String idDriver, LocalDate from, LocalDate to);
	
	@Query("SELECT r.driver, COALESCE(SUM(r.returnDate - r.plannedReturnDate), 0) FROM RentRecord r "
			+ "WHERE NOT(r.rentDate = NULL) AND r.returnDate > r.plannedReturnDate GROUP BY r.driver ORDER BY SUM(r.returnDate - r.plannedReturnDate)")
	List<DriverNumber> findMostSloopyDrivers(); 
	
	@Query("SELECT COALESCE(SUM(r.total), 0) FROM RentRecord r WHERE r.recordClosed = true AND (r.rentDate BETWEEN ?1 AND ?2)")
	double getTotal(LocalDate from, LocalDate to);
	
	@Query("SELECT COALESCE(SUM(r.total - r.damagesRepairPrice - (1 - r.fuelInTankPercent/100)*c.fuelPrice*r.car.model.tankVolume), 0) "
			+ "FROM RentRecord r JOIN RccConfig c ON c.id=1 WHERE r.recordClosed=true AND (r.rentDate BETWEEN ?1 AND ?2)")
	double findIncomInDateRange(LocalDate from, LocalDate to);
	
	@Query("SELECT r FROM RentRecord r WHERE NOT(r.returnDate = NULL) AND r.plannedReturnDate < r.returnDate AND (r.rentDate BETWEEN ?1 AND ?2)")
	List<RentRecord> findRecordsDelayedInRentDateRange(LocalDate from, LocalDate to);
	
	@Query("SELECT COALESCE(SUM(r.total), 0) FROM RentRecord r WHERE r.recordClosed = true AND r.car LIKE ?1 AND (r.rentDate BETWEEN ?2 AND ?3)")
	double findTotalByCarInDateRange(Car car, LocalDate from, LocalDate to);
	
	@Query("SELECT COALESCE(SUM(r.total), 0) FROM RentRecord r WHERE r.recordClosed = true AND (r.car.model.idModel = ?1) AND (r.rentDate BETWEEN ?2 AND ?3)")
	double findTotalByModelInDateRange(String idModel, LocalDate from, LocalDate to);
	
	@Query("SELECT COALESCE(SUM(r.total - r.damagesRepairPrice - (1 - r.fuelInTankPercent/100)*c.fuelPrice*r.car.model.tankVolume), 0) "
			+ "FROM RentRecord r JOIN RccConfig c ON c.id=1 WHERE r.recordClosed=true AND r.car.idCar LIKE ?1 AND (r.rentDate BETWEEN ?2 AND ?3)")
	double findIncomeByCarInDateRange(String idCar, LocalDate from, LocalDate to);
	
	@Query("SELECT r.car, COUNT(*) FROM RentRecord r GROUP BY r.car ORDER BY COUNT(*) DESC")
	List<CarNumber> findMostUsedCars();
	
	@Query("SELECT SUM(r.damagesRepairPrice) FROM RentRecord r WHERE r.recordClosed = true AND (r.rentDate BETWEEN ?1 AND ?2)")
	double findTotalDamagesRepairPriceInDateRange(LocalDate from, LocalDate to);
}
