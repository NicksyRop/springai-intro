package com.nicksy.openai.advisors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;


@Slf4j
public class TokenUsageAdvisor implements CallAdvisor {
    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {

        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
        ChatResponse chatResponse = chatClientResponse.chatResponse();

        if (chatResponse != null) {
            Usage usage = chatResponse.getMetadata().getUsage();
            log.info("Usage is {}", usage.getCompletionTokens());
        }

        //return chat response so the next advisor can use it
        return chatClientResponse;
    }

    @Override
    public String getName() {
        return "TokenUsageAdvisor";
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
