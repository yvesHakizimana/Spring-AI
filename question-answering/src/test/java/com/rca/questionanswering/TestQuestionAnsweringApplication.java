package com.rca.questionanswering;

import org.springframework.boot.SpringApplication;

public class TestQuestionAnsweringApplication {

    public static void main(String[] args) {
        SpringApplication.from(QuestionAnsweringApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
