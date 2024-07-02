package com.rca.promptbasics;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    private final ChatService chatService;

    ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @PostMapping("/chat/simple")
    String chatWithText(@RequestBody String input){
        return chatService.chatWithText(input);
    }

    @PostMapping("/chat/prompt")
    String chatWithPrompt(@RequestBody String input){
        return chatService.chatWithPrompt(input).getResult().getOutput().getContent();
    }

    @PostMapping("/chat/full")
    ChatResponse chatWithFull(@RequestBody String input){
        return chatService.chatWithPrompt(input);
    }
}
