package hu.webuni.pl.kamarasd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hu.webuni.pl.kamarasd.dto.DemoUserDto;

@Service
public class InitApp {
	
	//@Autowired
	//PasswordEncoder passwordEncoder;
	
	public void createDemoUser() {
		//DemoUserDto demoUser = new DemoUserDto(1L, "Teszt Elek", "user1", passwordEncoder.encode("pass1") );
	}
	
}
