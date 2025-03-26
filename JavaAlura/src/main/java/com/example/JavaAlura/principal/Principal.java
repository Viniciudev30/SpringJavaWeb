package com.example.JavaAlura.principal;

import com.example.JavaAlura.Model.DadosEpisodio;
import com.example.JavaAlura.Model.DadosSerie;
import com.example.JavaAlura.Model.DadosTemporada;
import com.example.JavaAlura.Service.ConsumoApi;
import com.example.JavaAlura.Service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=18bacbdb";

    public void exibMenu() {
        System.out.println("Digite o nome da série para a busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
            ;
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);

        //    for (int i = 0;i < dados.totalTemporadas(); i++){
        //    List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
        //    for (int j = 0;j< episodiosTemporada.size(); j++){
        //        System.out.println(episodiosTemporada.get(j).titulo());
        //    }
        // }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("\n top 5 episodios");
        dadosEpisodios.stream()
                .filter(e -> !e.availacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::availacao).reversed())
                .limit(5)
                .forEach(System.out::println);
    }
}

