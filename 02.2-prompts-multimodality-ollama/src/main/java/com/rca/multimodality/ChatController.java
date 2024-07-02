package com.rca.multimodality;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/chat/image/file")
    String chatFromImageFile(@RequestParam(defaultValue = "What do you see in this picture?") String message) throws IOException{
        return chatService.chatFromImageFile(message);
    }
}
