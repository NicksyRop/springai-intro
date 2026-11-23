package com.nicksy.openai.configs;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient openAiChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.create(openAiChatModel);
    }

    @Bean
    public ChatClient ollamaChatClient(OllamaChatModel ollamaChatModel) {
        ChatClient.Builder builder = ChatClient.builder(ollamaChatModel)
                        .defaultSystem("""
                                        You are an internal HR assistant ,your role is help  employees with questions related to HR policies
                                        such as leave policies and working hours.If user asks anything outside this topics
                                        kindly inform them that you only answer HR related policies
                                       """);
        //the builder gives us more options like default system  options etc check builder. methods
        //return ChatClient object
        return builder.build();
    }
}
