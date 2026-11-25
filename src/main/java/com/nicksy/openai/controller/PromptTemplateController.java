package com.nicksy.openai.controller;


import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PromptTemplateController {
    private final ChatClient ollamaChatClient;

//    @Value("classpath :/promptTemplates/userPromptTemplate.ts")
//    Resource userPromptTemplate;


    String promptTemplate = """
        A customer named {customerName} sent the following email:
        "{customerEmail}"

        Write a polite, professional, and helpful response addressing
        the customer's concerns.

        Use a friendly and empathetic tone. Keep the response clear and concise.

        Return only the email body. Do not include a subject or signature.
        """;


    @GetMapping("/email")
    public String email(@RequestParam String customerName , @RequestParam String customerEmail) {

        return ollamaChatClient.prompt()
                .user(userSpec -> userSpec
                        .text(promptTemplate)
                        .param("customerName", customerName)
                        .param("customerEmail", customerEmail))
                .call()
                .content();
    }


}
