package com.czf.ai.lab.controller;

import com.czf.ai.lab.server.model.TaskRequest;
import com.czf.ai.lab.server.model.TaskResponse;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

//    private final ChatClient chatClient;
//
//
//
//
//    @PostMapping("/execute")
//    public TaskResponse executeTask(@RequestBody TaskRequest request) {
//        TaskResponse response = new TaskResponse();
//        TaskResponse.TaskResult result = new TaskResponse.TaskResult();
//
//        String answer = chatClient.prompt(request.getParam().getInput()).call().content();
//
//        result.setAnswer(answer);
//        response.setData(result);
//        return response;
//    }
}