package com.rca.datextraction;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final StructuredDataExtractionService service;

    Controller(StructuredDataExtractionService service){
        this.service =  service;
    }

    @PostMapping("/extract")
    PatientJournal extract(@RequestBody String input){
        return service.extract(input);
    }
}
