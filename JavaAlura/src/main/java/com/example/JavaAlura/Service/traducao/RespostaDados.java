package com.example.JavaAlura.Service.traducao;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RespostaDados(@JsonAlias(value = "translatedText") String textoTraduzido) {
}
