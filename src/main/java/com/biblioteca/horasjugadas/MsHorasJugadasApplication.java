package com.biblioteca.horasjugadas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsHorasJugadasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsHorasJugadasApplication.class, args);
	}

}
