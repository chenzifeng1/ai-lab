package com.czf.ai.lab.server.provider;

import com.czf.ai.lab.server.StopOrderServer;
import com.czf.ai.lab.server.TodoServer;
import com.czf.ai.lab.server.WeatherService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类名：ProvoiderConfig
 * 描述：
 * 作者：zifengchen
 * 日期：2025/5/10
 */
@Configuration
public class ProviderConfig {

    @Bean
    public ToolCallbackProvider myTools(TodoServer todoServer,
                                        WeatherService weatherService,
                                        StopOrderServer stopOrderServer) {
        // 注册工具
        return MethodToolCallbackProvider
                .builder()
                .toolObjects(todoServer, weatherService, stopOrderServer)
                .build();

    }
}
