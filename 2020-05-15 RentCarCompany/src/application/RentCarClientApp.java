package application;

import client.RentCarClientBossController;
import client.RentCarServiceProxyImpl;
import client.SecurityClientController;
import client.SecurityServiceProxyImpl;

public class RentCarClientApp {

	public static void main(String[] args) {
		
		//RentCarServiceProxyImpl service = new RentCarServiceProxyImpl();
		//new RentCarClientController(service).run();
		SecurityServiceProxyImpl service = new SecurityServiceProxyImpl();
		new SecurityClientController(service).run();

	}

}
