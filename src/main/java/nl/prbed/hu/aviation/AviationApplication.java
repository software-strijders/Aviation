package nl.prbed.hu.aviation;

import nl.prbed.hu.aviation.application.AircraftService;
import nl.prbed.hu.aviation.domain.Type;
import nl.prbed.hu.aviation.domain.factory.TypeFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AviationApplication {
	public static void main(String[] args) {
		SpringApplication.run(AviationApplication.class, args);
	}
}