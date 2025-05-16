package com.czf.ai.lab.server.model;

import lombok.Data;

@Data
public class TaskRequest {
    private Long applicationId;
    private String businessType;
    private String businessId;
    private String intent;
    private TaskParam param;
    private String rawInput;

    @Data
    public static class TaskParam {
        private String input;
        private String ucId;
    }
}