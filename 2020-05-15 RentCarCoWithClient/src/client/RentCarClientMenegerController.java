package client;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import application.business.dto.CarDto;
import application.business.dto.DriverDto;
import view.ConsoleInputOutput;
import view.Item;
import view.Menu;

public class RentCarClientMenegerController {

	@Autowired RentCarServiceProxyImpl service;
	
	public RentCarClientMenegerController(RentCarServiceProxyImpl service) {
		this.service = service;
	}

	public void run() {
		new Menu(
			"MANAGER",
			Item.of("Add Driver", io -> {
				String[] driverData = io.readString("Enter Id FirstName LastName BirthDate Telephon Email").split(" ");
					System.out.println(service.addDriver(new DriverDto(driverData[0], driverData[1], driverData[2], driverData[3], driverData[4], driverData[5])));	
			}),
			Item.of("Add Car", io -> {
				String[] carData = io.readString("Enter IdCar IdModel").split(" ");
					System.out.println(service.addCar(new CarDto(carData[0], carData[1])));	
			}),	
			
			Item.of("Get All Cars", io -> {
					System.out.println(service.getAllCars());	
			}),
			Item.of("Get Total In Date Range", io -> {
				String[] data = io.readString("Enter StartDate FinishDate").split(" ");
				LocalDate from = LocalDate.parse(data[0]);
				LocalDate to = LocalDate.parse(data[1]);
					System.out.println(service.getTotalInDateRange(from, to));	
			}),
			Item.of("Get Record", io -> {
					System.out.println(service.getRecord(io.readInt("Enter IdRecord")));	
			}),
			Item.exit())
	.perform(new ConsoleInputOutput());
	}
}
