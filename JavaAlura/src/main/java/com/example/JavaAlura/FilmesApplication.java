package com.example.JavaAlura;

import com.example.JavaAlura.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilmesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FilmesApplication.class, args);
	}

	@Override
	public void run(String... args) {
        Principal principal = new Principal();
        principal.exibMenu();

	}
}