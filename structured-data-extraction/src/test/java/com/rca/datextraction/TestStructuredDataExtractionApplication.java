package com.rca.datextraction;

import org.springframework.boot.SpringApplication;

public class TestStructuredDataExtractionApplication {

    public static void main(String[] args) {
        SpringApplication.from(StructuredDataExtractionApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
