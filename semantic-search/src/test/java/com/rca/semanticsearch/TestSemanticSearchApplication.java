package com.rca.semanticsearch;

import org.springframework.boot.SpringApplication;

public class TestSemanticSearchApplication {

    public static void main(String[] args) {
        SpringApplication.from(SemanticSearchApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
