package com.rca.chatbot;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    private final ChatbotService chatbotService;

    ChatController(ChatbotService chatbotService){
        this.chatbotService = chatbotService;
    }

    @PostMapping("/chat/{chatId}")
    String chat(@PathVariable String chatId, @RequestBody String input){
        return chatbotService.chat(chatId, input);
    }

}
