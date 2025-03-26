package com.example.JavaAlura.principal;

import com.example.JavaAlura.Service.ConsumoApi;

import java.util.Scanner;
public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=18bacbdb";
    public void exibMenu(){
        System.out.println("Digite o nome da série para a busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);

        //"https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c"
    }
}

