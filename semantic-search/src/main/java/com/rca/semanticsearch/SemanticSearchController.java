package com.rca.semanticsearch;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SemanticSearchController {
    private final SemanticSearchService semanticSearchService;

    public SemanticSearchController(SemanticSearchService semanticSearchService){
        this.semanticSearchService = semanticSearchService;
    }

    @PostMapping("/semantic-search")
    List<InstrumentNote> semanticSearch(@RequestBody String query){
        return semanticSearchService.semanticSearch(query);
    }
}
