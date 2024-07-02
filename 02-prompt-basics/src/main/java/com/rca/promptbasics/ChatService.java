package com.rca.promptbasics;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    //Initializing the chatClient.
    private final ChatClient chatClient;

    ChatService(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }

    //Prompting the model
    String chatWithText(String message){
        return chatClient
                .prompt()
                .user(message)
                .call()
                .content();
    }

    //Prompting the model with a prompt.
    ChatResponse chatWithPrompt(String message){
        return chatClient
                .prompt(new Prompt(message))
                .call()
                .chatResponse();

    }

}
