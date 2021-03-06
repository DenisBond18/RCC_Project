package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import application.business.dto.RccConfigDto;
import application.business.services.BossServiceImpl;

@SpringBootApplication
public class RentCarCompanyApp {

	public static void main(String[] args) {
		SpringApplication.run(RentCarCompanyApp.class, args); 
	}

}
