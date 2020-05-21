package application.business.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.business.config.RccConfig;
import application.business.dto.CarDto;
import application.business.dto.DtoService;
import application.business.dto.ModelDto;
import application.business.dto.RccConfigDto;
import application.business.entities.Car;
import application.business.entities.CarState;
import application.business.entities.Model;
import application.business.repositories.CarJpaRepository;
import application.business.repositories.ModelJpaRepository;
import application.business.repositories.RccConfigJpaRepository;


@Service
@Transactional
public class BossServiceImpl implements BossService{

	@Autowired RccConfigJpaRepository configRepo;
	@Autowired ModelJpaRepository modelRepo;
	@Autowired CarJpaRepository carRepo;
	
	@Autowired DtoService dtoService;
	
	@Override
	public RccConfigDto setConfig(RccConfigDto config) {
		RccConfig rccConfig = dtoService.getRccConfig(config);
		configRepo.save(rccConfig);
		return config;
	}

	@Override
	public ModelDto addModel(ModelDto model) {
		modelRepo.checkById(model.getIdModel(), false);
		modelRepo.save(dtoService.getModel(model));
		return model;
	}

	@Override
	public ModelDto setModelDailyRate(String idModel, double dailyRate) {
		Model model = modelRepo.checkById(idModel, true);
		model.setDailyRate(dailyRate);
		modelRepo.save(model);
		return dtoService.getModelDto(model);
	}

	@Override
	public CarDto setCarWrittenOff(String idCar) {
		Car car = carRepo.checkById(idCar, true);
		if(car.getState().equals(CarState.AVAILABLE)) {
			car.setState(CarState.WRITTEN_OFF);
			carRepo.save(car);
		}
		return dtoService.getCarDto(car);
	}

}
