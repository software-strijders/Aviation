package nl.prbed.hu.aviation;

import nl.prbed.hu.aviation.application.AircraftService;
import nl.prbed.hu.aviation.domain.Type;
import nl.prbed.hu.aviation.domain.factory.TypeFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class AviationApplication {
	public static void main(String[] args) {
		SpringApplication.run(AviationApplication.class, args);
	}
}
