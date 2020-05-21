package client;

import org.springframework.beans.factory.annotation.Autowired;

import view.ConsoleInputOutput;
import view.Item;
import view.Menu;

public class RentCarClientAdminController {

	@Autowired RentCarClientSecurityImpl service;
	
	public RentCarClientAdminController(RentCarClientSecurityImpl service) {
		super();
		this.service = service;
	}

	public void run() {
		new Menu(
			"ADMIN",
			Item.of("Add Account", io -> {
				String[] data = io.readString("Enter Login Password Role").split(" ");
					System.out.println(service.addAccount(data[0], data[1], data[2]));	
			}),
			Item.of("Get All Accounts", io -> {
					System.out.println(service.getAllAccounts());	
			}),	
			Item.exit())
	.perform(new ConsoleInputOutput());
	}
}
