package client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

import application.business.dto.CarDto;
import application.business.dto.DriverDto;
import application.business.dto.ModelDto;
import application.business.dto.RccConfigDto;

import view.ConsoleInputOutput;
import view.Item;
import view.Menu;

public class RentCarClientBossController {
	
	@Autowired RentCarServiceProxyImpl service;

	
	public RentCarClientBossController(RentCarServiceProxyImpl service) {
		super();
		this.service = service;
	}
	public void run() {
		new Menu(
			"BOSS",
			Item.of("Set Config", io -> {
				String[] configData = io.readString("Enter DelayPenalty FuelPrice").split(" ");
					System.out.println(service.setConfig(new RccConfigDto(Double.parseDouble(configData[0]), Double.parseDouble(configData[1]))));	
			}),
			Item.of("Add Model", io -> {
				String[] modelData = io.readString("Enter IdModel DailyRate TankVolume").split(" ");
					System.out.println(service.addModel(new ModelDto(modelData[0], Double.parseDouble(modelData[1]), Double.parseDouble(modelData[2]))));	
			}),	
			Item.exit())
	.perform(new ConsoleInputOutput());
	}
	
}
