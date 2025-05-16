package com.czf.ai.lab.server.model;

import lombok.Data;

@Data
public class TaskResponse {
    private Integer code = 0;
    private String message = "success";
    private TaskResult data;

    @Data
    public static class TaskResult {
        private String answer;
        private String type = "text";
    }
}