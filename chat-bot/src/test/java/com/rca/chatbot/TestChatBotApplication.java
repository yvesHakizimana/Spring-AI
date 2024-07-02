package com.rca.chatbot;

import org.springframework.boot.SpringApplication;

public class TestChatBotApplication {

    public static void main(String[] args) {
        SpringApplication.from(ChatBotApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
