package com.nicksy.openai.configs;

import com.nicksy.openai.advisors.TokenUsageAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.List;

@Configuration
public class ChatClientConfig {
    @Value("classpath:promptTemplates/systemPromptTemplate.st")
    Resource systemPromptTemplate;

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
//                .defaultSystem("""
//                                        You are a professional customer service assistant which helps in drafting email response
//                                        to improve productivity of customer support team
//                                       """)
          //      .defaultUser("How can you help me?") //this will be the default user prompt if not message is passed in the controller in user(message)
                .defaultSystem(systemPromptTemplate)
                .defaultAdvisors(List.of(new SimpleLoggerAdvisor(), new TokenUsageAdvisor()))
                .build();
        //the builder gives us more options like default system  options etc check builder. methods
        //return ChatClient object
      //  return builder.build();
    }
}
