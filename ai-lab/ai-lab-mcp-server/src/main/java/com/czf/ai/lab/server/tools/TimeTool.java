package com.czf.ai.lab.server.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

/**
 * 类名：TimeUtils
 * 描述：时间MCP工具
 * 作者：zifengchen
 * 日期：2025/6/3
 */
@Service
public class TimeTool implements CustomMcpTool {

    /**
     * 获取当前时间戳
     *
     * @return 当前时间戳
     */
    public long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间字符串
     *
     * @return 当前时间字符串
     */
    public String getCurrentTimeString() {
        return java.time.LocalDateTime.now().toString();
    }

    /**
     * 获取当前时间的格式化字符串
     *

     * @return 格式化后的当前时间字符串
     */
    @Tool(description = "需要获取当前时间的时候很有用,格式为yyyy-MM-dd HH:mm:ss")
    public String getNowDate() {
        return java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
}
