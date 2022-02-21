package hu.webuni.pl.kamarasd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.pl.kamarasd.service.InitApp;

@SpringBootApplication
public class PackageLogisticApplication implements CommandLineRunner {

	@Autowired
	InitApp initApp;
		
	public static void main(String[] args) {
		SpringApplication.run(PackageLogisticApplication.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		initApp.createDemoUser();
		System.out.println("STARTED");
		
	}

}
