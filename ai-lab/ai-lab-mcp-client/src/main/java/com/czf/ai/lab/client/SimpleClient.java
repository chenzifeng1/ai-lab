package com.czf.ai.lab.client;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 类名：SimpleClient
 * 描述：mcp 自定义client
 * 作者：zifengchen
 * 日期：2025/4/24
 */
@Component
public class SimpleClient {


    ToolCallbackProvider tools;

    ChatClient.Builder chatClientBuilder;

    ChatClient chatClient;


    @Autowired
    public SimpleClient(ChatClient.Builder chatClientBuilder, ToolCallbackProvider tools) {
        this.tools = tools;
        this.chatClientBuilder = chatClientBuilder;

        chatClient = chatClientBuilder
//                .defaultSystem()
                .defaultTools(tools)
                .build();
    }

    /**
     * 聊天
     * @param query
     * @return
     */
    public String chat(String query) {




        return chatClient.prompt(query).call().content();
    }


    public FunctionCallback[] tools() {
        return tools.getToolCallbacks();
    }



}
