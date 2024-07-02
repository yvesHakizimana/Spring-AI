package com.rca.semanticsearch;

import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemanticSearchService {
    private final VectorStore vectorStore;

    public SemanticSearchService(VectorStore vectorStore){
        this.vectorStore = vectorStore;
    }

    List<InstrumentNote> semanticSearch(String query){
        return vectorStore.similaritySearch(SearchRequest.query(query).withTopK(3))
                .stream()
                .map(document -> new InstrumentNote(document.getContent()))
                .toList();
    }
}
