package com.czf.ai.lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 类名：${NAME}
 * 描述：${DESCRIPTION}
 * 作者：zifengchen
 * 日期：2025/4/24
 */
@SpringBootApplication(exclude = {
        org.springframework.ai.autoconfigure.mcp.client.SseHttpClientTransportAutoConfiguration.class
})
public class McpClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(McpClientApplication.class, args);
    }
}