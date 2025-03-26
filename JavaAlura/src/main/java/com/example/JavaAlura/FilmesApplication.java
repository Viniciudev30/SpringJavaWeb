package com.example.JavaAlura;

import com.example.JavaAlura.Model.DadosEpisodio;
import com.example.JavaAlura.Model.DadosSerie;
import com.example.JavaAlura.Model.DadosTemporada;
import com.example.JavaAlura.Service.ConsumoApi;
import com.example.JavaAlura.Service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FilmesApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FilmesApplication.class, args);
	}

	@Override
	public void run(String... args) {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?t=The+Flash&apikey=18bacbdb");
		System.out.println(json);
        ConverteDados conversor = new ConverteDados();
		DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);
		json = consumoApi.obterDados("https://www.omdbapi.com/?t=The+Flash&Season=1&episode=1&apikey=18bacbdb");
		DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio);

		List<DadosTemporada> temporadas = new ArrayList<>();

		for(int i = 1; i<=dados.totalTemporadas(); i++){
			json = consumoApi.obterDados("https://www.omdbapi.com/?t=The+Flash&season=" + i + "&apikey=18bacbdb");
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);

		}
		temporadas.forEach(System.out::println);
	}
}