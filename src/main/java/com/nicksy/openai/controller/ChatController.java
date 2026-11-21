package com.nicksy.openai.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ChatController {
    private final ChatClient ollamaChatClient;
    private final ChatClient openAiChatClient;

    @GetMapping("/openai/chat")
    public String openAiChat(@RequestParam String message) {
        return  openAiChatClient.prompt(message)
                .call()
                .content();
    }

    @GetMapping("/ollama/chat")
    public String ollamaChat(@RequestParam String message) {
        return  ollamaChatClient.prompt(message)
                .call()
                .content();
    }
}
