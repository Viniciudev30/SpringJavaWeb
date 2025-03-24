package com.example.JavaAlura.Service;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}