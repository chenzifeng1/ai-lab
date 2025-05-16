package com.czf.ai.lab.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 类名：AMapClient
 * 描述：
 * 作者：zifengchen
 * 日期：2025/4/24
 */
@Component
public class AMapClient {

    @Value("${ai.user.input}")
    private String userInput;

//    @Bean
//    public CommandLineRunner predefinedQuestions(ChatClient.Builder chatClientBuilder, ToolCallbackProvider tools, ConfigurableApplicationContext context) {
//
//        return args -> {
//            var chatClient = chatClientBuilder
//                    .defaultTools(tools)
//                    .build();
//
//            System.out.println("\n>>> QUESTION: " + userInput);
//            System.out.println("\n>>> ASSISTANT: " + chatClient.prompt(userInput).call().content());
//
//            context.close();
//        };
//    }
}
