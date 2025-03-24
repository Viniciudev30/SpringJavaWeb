package com.example.JavaAlura;

import com.example.JavaAlura.Model.DadosSerie;
import com.example.JavaAlura.Service.ConsumoApi;
import com.example.JavaAlura.Service.ConverteDados;
import com.example.JavaAlura.Service.IConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilmesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FilmesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=The+Flash&apikey=18bacbdb");
		System.out.println(json);
		// json = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
		// System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);
	}
}