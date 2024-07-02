package com.rca.promptsollama;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    private final ChatService chatService;

    ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat/user")
    String chatWithUserMessageTemplate(@RequestBody MusicQuestion question) {
        return chatService.chatWithUserMessageTemplate(question);
    }

    @PostMapping("/chat/system")
    String chatWithSystemMessageTemplate(@RequestBody String question) {
        return chatService.chatWithSystemMessageTemplateExternal(question);
    }

    @PostMapping("/chat/external")
    String chatWithSystemMessageTemplateExternal(@RequestBody String question) {
        return chatService.chatWithSystemMessageTemplateExternal(question);
    }
}
