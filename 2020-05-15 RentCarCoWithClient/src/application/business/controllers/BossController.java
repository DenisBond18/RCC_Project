package application.business.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.business.dto.CarDto;
import application.business.dto.ModelDto;
import application.business.dto.RccConfigDto;
import application.business.services.BossService;

@RestController
@CrossOrigin
@RequestMapping("/boss")
@Validated
public class BossController {

	@Autowired BossService service;
	
	@PostMapping("/set_config")
	public RccConfigDto setConfig(@RequestBody @Valid RccConfigDto config) {
		return service.setConfig(config);
	}
	@PostMapping("/add_model")
	public ModelDto addModel(@RequestBody @Valid ModelDto model) {
		return service.addModel(model);
	}
	@PutMapping("/set_daily_rate")
	public ModelDto setDailyRate(String idModel, double dailyRate) {
		return service.setModelDailyRate(idModel, dailyRate);
	}
	@PutMapping("/set_car_written_off")
	public CarDto setCarWrittenOff(String idCar) {
		return service.setCarWrittenOff(idCar);
	}
}
