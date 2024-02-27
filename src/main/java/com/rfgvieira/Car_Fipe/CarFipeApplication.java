package com.rfgvieira.Car_Fipe;

import com.rfgvieira.Car_Fipe.main.Main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CarFipeApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Main principal = new Main();
		principal.menu();
	}
}
