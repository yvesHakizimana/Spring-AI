package com.rca.datextraction;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptionsBuilder;
import org.springframework.stereotype.Service;

@Service
public class StructuredDataExtractionService {

    private final ChatClient chatClient;

    StructuredDataExtractionService(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder
                .defaultOptions(ChatOptionsBuilder.builder()
                        .withTemperature(0.0f)
                        .build())
                .build();
    }

    //Extracting the data from a given message and then saving it as patient journal test case.
    PatientJournal extract(String message){
        return chatClient
                .prompt()
                .user(
                        userSpec -> userSpec.text(
                                """
                                        Extract structured data from the provided text.
                                        If you do not know the value of a field asked to extract,
                                        do not include any value for the field in the result.
                                        Finally save the object in the database.
                                        
                                        ________________
                                        TEXT:
                                        {text}
                                        ________________
                                        """
                        )
                                .param("text", message)
                )
                .call()
                .entity(PatientJournal.class);
    }
}
