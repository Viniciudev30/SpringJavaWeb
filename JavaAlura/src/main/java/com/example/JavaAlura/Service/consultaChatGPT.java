package com.example.JavaAlura.Service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
public class consultaChatGPT {
    public static String obterTraducao(String texto) {
        OpenAiService service = new OpenAiService("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");
        CompletionRequest requisicao = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("traduza para o português o texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var resposta = service.createCompletion(requisicao);
        return resposta.getChoices().get(0).getText();
    }
}
