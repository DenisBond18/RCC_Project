package application.business.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import application.business.entities.Model;
import application.common.CustomizedJpaRepository;

public interface ModelJpaRepository extends CustomizedJpaRepository<Model, String>{

	@Override
	default String type() {return "Model";}
	
	@Query("SELECT DISTINCT c.model FROM Car c WHERE c.state = application.business.entities.CarState.AVAILABLE")
	List<Model> findModelsAvailable();
	
	@Query("SELECT m FROM Model m WHERE m NOT IN (SELECT DISTINCT(c.model) FROM Car c)")
	List<Model> findModelsEmpty();
}
