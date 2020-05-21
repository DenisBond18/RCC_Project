package application.business.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import application.business.entities.Car;
import application.business.entities.CarState;
import application.business.entities.Model;
import application.common.CustomizedJpaRepository;

public interface CarJpaRepository extends CustomizedJpaRepository<Car, String>{

	@Override
	default String type() {
		return "Car";
	}

	List<Car> findByStateEquals(CarState state);
	List<Car> findByModelEquals(Model model);
	@Query("SELECT c FROM Car c WHERE c.state = application.business.entities.CarState.AVAILABLE AND c.model.idModel = ?1")
	List<Car> findCarsAvailableByModel(String idModel);
	
}
