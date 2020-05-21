package application.security.controller;

import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
@ManagedResource
public class ApplicationController {
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	@GetMapping("/manager")
	public String manager() {
		return "manager";
	}
	
	@GetMapping("/user")
	public String user() {
		return "user";
	}
	
	@GetMapping("/everybody")
	public String everybody() {
		return "everybody";
	}

}
