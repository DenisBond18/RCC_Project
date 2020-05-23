package client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

import view.ConsoleInputOutput;
import view.Item;
import view.Menu;

public class RentCarClientClerkController {

	@Autowired RentCarServiceProxyImpl service;
	
	public void run() {
		new Menu(
			"CLERK",
			Item.of("Rent Car", io -> {
				String[] data = io.readString("Enter IdCar IdDriver PlannedReturnDate").split(" ");
				LocalDate from = LocalDate.parse(data[2], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					System.out.println(service.rentCar(data[0], data[1], from));
			}),
			Item.of("Receive Car", io -> {
				String[] data = io.readString("Enter IdRecord FuelInTankPercent NoDamages").split(" ");
					System.out.println(service.receiveCar(Integer.parseInt(data[0]), Double.parseDouble(data[1]), Boolean.getBoolean(data[2])));	
			}),	
			Item.exit())
	.perform(new ConsoleInputOutput());
	}
}
