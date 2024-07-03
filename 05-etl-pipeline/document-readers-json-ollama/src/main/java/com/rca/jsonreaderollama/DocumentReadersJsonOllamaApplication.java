package com.rca.jsonreaderollama;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DocumentReadersJsonOllamaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentReadersJsonOllamaApplication.class, args);
    }

    @Bean
    VectorStore vectorStore(EmbeddingModel embeddingModel){
        return new SimpleVectorStore(embeddingModel);
    }
}
