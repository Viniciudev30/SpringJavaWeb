package com.example.JavaAlura.Service;

import com.example.JavaAlura.Service.traducao.TraducaoDados;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;

public class ConsultaMinhaMemoria {
    public static String obterTraducao(String text) {
        ObjectMapper mapper = new ObjectMapper();

        ConsumoApi consumo = new ConsumoApi();

        String texto = URLEncoder.encode(text);
        String langpair = URLEncoder.encode("en|pt-br");

        String url = "https://api.mymemory.translated.net/get?q=" + texto + "&langpair=" + langpair;

        String json = consumo.obterDados(url);

        TraducaoDados traducao;
        try {
            traducao = mapper.readValue(json, TraducaoDados.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return traducao.respostaDados().textoTraduzido();
    }
}