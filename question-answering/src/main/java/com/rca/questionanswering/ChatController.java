package com.rca.questionanswering;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    private final ChatService chatService;

    ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @PostMapping("/chat/doc")
    String chatWithDocument(@RequestBody String input){
        return chatService.chatWithDocument(input);
    }

}
