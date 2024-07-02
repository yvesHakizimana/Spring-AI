package com.rca.promptmessages;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatClient chatClient;

    private final Resource systemMessageResource;

    ChatService(ChatClient.Builder chatClientBuilder, @Value("classpath:/prompts/system-message.st") Resource systemMessageResource){
        this.chatClient = chatClientBuilder.build();
        this.systemMessageResource = systemMessageResource;
    }

    //Chatting with a single message.
    String chatWithSingleMessage(String message){
        return chatClient
                .prompt()
                .user(message)
                .call()
                .content();
    }

    //Chatting with multiple messages.
    String chatWithMultipleMessage(String message){
        var systemMessage = """
                You are a helpful and polite assistant.
                Answer in one sentence using a very formal language
                and starting the answer with a formal greeting.
                """;
        return chatClient
                .prompt()
                .system(systemMessage)
                .user(message)
                .call()
                .content();
    }

    //Chatting with the external messages.
    String chatWithExternalMessages(String message){
        var systemMessage = new SystemMessage(systemMessageResource);
        var userMessage = new UserMessage(message);
        return chatClient
                .prompt()
                .messages(systemMessage, userMessage)
                .call()
                .content();
    }





}
