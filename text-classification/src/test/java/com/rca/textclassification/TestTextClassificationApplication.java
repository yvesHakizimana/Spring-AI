package com.rca.textclassification;

import org.springframework.boot.SpringApplication;

public class TestTextClassificationApplication {

    public static void main(String[] args) {
        SpringApplication.from(TextClassificationApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
