package com.rca.promptsollama;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ChatService {

    private final ChatClient chatClient;
    private final Resource systemMessageResource;

    ChatService(ChatClient.Builder chatClient,
                @Value("classpath:/prompts/system-message.st") Resource systemMessageResource){
        this.chatClient = chatClient.build();
        this.systemMessageResource = systemMessageResource;
    }

    //**Template is the crafted prompt containing some input parameters from the user.
    String chatWithUserMessageTemplate(MusicQuestion question){
        var userPromptTemplate = """
                Tell me name and band of three musicians famous for playing in a {genre} band.
                Consider only the musicians that play the {instrument} in that band.
                """;
        return chatClient
                .prompt()
                .user(userSpec -> userSpec
                        .text(userPromptTemplate)
                        .param("instrument", question.instrument())
                        .param("genre", question.genre()))
                .call()
                .content();
    }

    String chatWithSystemMessageTemplateExternal(String message){
        return chatClient
                .prompt()
                .system(systemSpec -> systemSpec
                        .text(systemMessageResource)
                        .param("greeting", randomGreeting()))
                .user(message)
                .call()
                .content();
    }

    private String randomGreeting(){
        var names = List.of("Howdy", "Ahoy", "Well", "Well", "well");
        return names.get(new Random().nextInt(names.size()));
    }





}
