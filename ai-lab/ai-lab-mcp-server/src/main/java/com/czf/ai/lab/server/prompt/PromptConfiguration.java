package com.czf.ai.lab.server.prompt;

import com.alibaba.fastjson2.util.DateUtils;
import io.modelcontextprotocol.spec.McpSchema;
import io.modelcontextprotocol.server.McpServerFeatures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;

/**
 * 类名：PromptConfiguration
 * 描述：
 * 作者：zifengchen
 * 日期：2025/5/29
 */
@Configuration
public class PromptConfiguration {
    Logger log = LoggerFactory.getLogger(PromptConfiguration.class);


    @Bean
    public List<McpServerFeatures.SyncPromptRegistration> myPrompts() {


        return List.of(sendBroadcast());
    }


    @Bean
    public McpServerFeatures.SyncPromptRegistration sendBroadcast() {
        var prompt = new McpSchema.Prompt("sendBroadcast",  // prompt名称
                "为业主发送装修进度播报,你需要识别当前时间，并从中提取出项目地址和装修进度等信息", // prompt描述
                List.of(
                        new McpSchema.PromptArgument("address", "播报工地地址", true),
                        new McpSchema.PromptArgument("time", "发送播报的时间", true),
                        new McpSchema.PromptArgument("projectId", "项目id", true),
                        new McpSchema.PromptArgument("itemContent", "装修进度，比如说刷漆，水电施工等，如果有多项使用,拼接", true),
                        new McpSchema.PromptArgument("space", "施工作业房间", true)
                ));

        // 定义prompt具体的处理逻辑
        McpServerFeatures.SyncPromptRegistration sendBroadcast = new McpServerFeatures.SyncPromptRegistration(prompt, request -> {
            String address = (String) request.arguments().get("address");
            String time = (String) request.arguments().get("time");
            String projectId = (String) request.arguments().get("projectId");
            String itemContent = (String) request.arguments().get("itemContent");
            String space = (String) request.arguments().get("space");
            // todo 这里可以调用tools
            log.info("Creating broadcast with address: {}, date: {}, projectId: {}, itemContent: {}, space: {}",
                    address, time, projectId, itemContent, space);
            try {
                Date date = DateUtils.parseDate(time, "yyyy-MM-dd HH:mm:ss");
                log.info("Parsed date: {}", date);

                return new McpSchema.GetPromptResult("发送播报成功", List.of(
                        new McpSchema.PromptMessage(McpSchema.Role.ASSISTANT, new McpSchema.TextContent("播报已发送，地址：" + address
                                + "，时间：" + time
                                + "，项目ID：" + projectId
                                + "，内容：" + itemContent
                                + "，房间：" + space))
                ));
            } catch (Exception e) {
                log.error("Error creating todo task", e);
                return new McpSchema.GetPromptResult("发送播报失败 ", List.of(
                        new McpSchema.PromptMessage(McpSchema.Role.ASSISTANT, new McpSchema.TextContent("发送播报失败，错误信息：" + e.getMessage() + "。请检查输入参数是否正确.")
                        )));
            }
        });
        return sendBroadcast;
    }



}
