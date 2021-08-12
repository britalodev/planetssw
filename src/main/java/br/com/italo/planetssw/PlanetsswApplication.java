package br.com.italo.planetssw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PlanetsswApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanetsswApplication.class, args);
	}

}
