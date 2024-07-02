package com.rca.promptmessages;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    private final ChatService chatService;

    ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @PostMapping("/chat/single")
    String chatWithSingleMessage(@RequestBody String input) {
        return chatService.chatWithSingleMessage(input);
    }

    @PostMapping("/chat/multiple")
    String chatWithMultipleMessages(@RequestBody String input) {
        return chatService.chatWithMultipleMessage(input);
    }

    @PostMapping("/chat/external")
    String chatWithExternalMessage(@RequestBody String input) {
        return chatService.chatWithExternalMessages(input);
    }
}
