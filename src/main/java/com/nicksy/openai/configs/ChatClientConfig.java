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
        return ChatClient.builder(ollamaChatModel)
//                        .defaultSystem("""
//                                        You are an internal HR assistant ,your role is help  employees with questions related to HR policies
//                                        such as leave policies and working hours.If user asks anything outside this topics
//                                        kindly inform them that you only answer HR related policies
//                                       """)
                .defaultSystem("""
                                        You are a professional customer service assistant which helps in drafting email response 
                                        to improve productivity of customer support team
                                       """)
                .defaultUser("How can you help me?") //this will be the default user prompt if not message is passed in the controller in user(message)
                .build();
        //the builder gives us more options like default system  options etc check builder. methods
        //return ChatClient object
      //  return builder.build();
    }
}
