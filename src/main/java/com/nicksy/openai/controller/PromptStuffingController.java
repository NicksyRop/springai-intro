package com.nicksy.openai.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PromptStuffingController {

    private final ChatClient ollamaChatClient;



    @GetMapping("/hr")
    public String hrSupport(@RequestParam String message) {
        return ollamaChatClient.prompt()
               .user(message)
               .call()
               .content();
    }
}
