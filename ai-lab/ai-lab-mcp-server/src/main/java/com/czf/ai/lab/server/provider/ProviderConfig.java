package com.czf.ai.lab.server.provider;

import com.czf.ai.lab.server.tools.StopOrderTool;
import com.czf.ai.lab.server.tools.TimeTool;
import com.czf.ai.lab.server.tools.TodoTool;
import com.czf.ai.lab.server.tools.WeatherTool;
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
    public ToolCallbackProvider myTools(TodoTool todoTool,
                                        WeatherTool weatherTool,
                                        StopOrderTool stopOrderTool, TimeTool timeTool) {


        // 注册工具
        return MethodToolCallbackProvider
                .builder()
                .toolObjects(todoTool, weatherTool, stopOrderTool, timeTool)
                .build();

    }


}
