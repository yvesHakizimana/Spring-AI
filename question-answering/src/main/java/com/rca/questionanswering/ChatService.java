package com.rca.questionanswering;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    ChatService(ChatClient.Builder chatClientBuilder, VectorStore vectorStore){
        this.vectorStore = vectorStore;
        this.chatClient  = chatClientBuilder.build();
    }

    //Chatting with the documents passed in the ingestion line.
    String chatWithDocument(String message){
        return  chatClient
                .prompt()
                .advisors(new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults().withTopK(5)))
                .user(message)
                .call()
                .content();
    }
}

