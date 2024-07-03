package com.rca.embeddingmodel;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmbeddingController {

    //Model for converting tokens/texts into numerical vector representation.
    private final EmbeddingModel embeddingModel;

    EmbeddingController(EmbeddingModel embeddingModel){
        this.embeddingModel = embeddingModel;
    }

    @GetMapping("/embed")
    String embed(@RequestParam(defaultValue = "What is the capital city of Rwanda") String message){
        var embeddings = embeddingModel.embed(message);
        return "The size of the embedding vector: " +embeddings.size();
    }

    @GetMapping("/embed/ollama-options")
    String embedWithOllamaOptions(@RequestParam(defaultValue = "What is the capital city of Rwanda") String message) {
        var embeddings = embeddingModel.call(new EmbeddingRequest(List.of(message),
                OllamaOptions.create().withModel("mistral")))
                .getResult().getOutput();
        return "The size of the embedding Vector ::" + embeddings.size();
    }
}
