package com.czf.ai.lab.server;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 类名：TodoServer
 * 描述：
 * 作者：zifengchen
 * 日期：2025/5/10
 */
@Service
public class TodoServer {

    Logger log = LoggerFactory.getLogger(TodoServer.class);

    public static final Map<String, List<TodoTask>> todoList = new HashMap<>();

    @Tool(description = "用来创建用户的待办事项")
    public String createTask(@ToolParam(description = "任务类型") String type, // 后续尝试定义枚举
                             @ToolParam(description = "待办事项描述") String description,
                             @ToolParam(description = "待办事项日期") String date,
                             @ToolParam(description = "任务提出人的id") String ucId) {
        // 这里可以添加创建待办事项的逻辑
        log.info("Creating task with name: {}, description: {}, date: {}", type, description, date);

        TodoTask task = TodoTask.createWithGeneratedId(type, description, date, ucId);
        todoList.computeIfAbsent(ucId, k -> new java.util.ArrayList<>()).add(task);
        return "Task created successfully!";
    }

    @Tool(description = "用来查询用户的任务事项")
    public List<TodoTask> selectTodoList(@ToolParam(description = "用户id") String ucId) {
        log.info("Retrieving todo list for user with id: {}", ucId);
        return todoList.getOrDefault(ucId, List.of());
    }

    @Tool(description = "用来删除用户所有的任务事项，只有用户明确要求是删除所有任务时才使用该方法")
    public String deleteTodoListByUcId(@ToolParam(description = "用户id") String ucId) {
        log.info("Deleting todo list for user with id: {}", ucId);
        List<TodoTask> todoTasks = todoList.get(ucId);
        if (todoTasks != null) {
            int size = todoTasks.size();
            todoList.remove(ucId);
            return "Todo list deleted successfully!, deleted " + size + " tasks.";
        } else {
            return "No todo list found for user with id: " + ucId;
        }
    }

    @Tool(description = "用来删除用户的单个任务事项")
    public String deleteOneByUcIdAndTaskId(@ToolParam(description = "用户id") String ucId,
                                           @ToolParam(description = "任务id") String taskId) {
        log.info("Deleting task with id: {} for user with id: {}", taskId, ucId);
        List<TodoTask> todoTasks = todoList.get(ucId);
        if (CollectionUtils.isEmpty(todoTasks)) {
            return "Todo list is empty for user with id: " + ucId;
        } else {
            for (TodoTask task : todoTasks) {
                if (task.id.equals(taskId)) {
                    todoTasks.remove(task);
                    return "delete task:" + JSONObject.toJSONString(task);
                }
            }
        }
        return "Task with id: " + taskId + " not found for user with id: " + ucId;
    }

    @Tool
    public String updateTask(@ToolParam(description = "用户id") String ucId,
                                 @ToolParam(description = "任务id") String taskId,
                                 @ToolParam(description = "任务类型") String type,
                                 @ToolParam(description = "待办事项描述") String description,
                                 @ToolParam(description = "待办事项日期") String date) {
        log.info("Updating task with id: {} for user with id: {}", taskId, ucId);
        List<TodoTask> todoTasks = todoList.get(ucId);
        if (CollectionUtils.isEmpty(todoTasks)) {
            return "Todo list is empty for user with id: " + ucId;
        } else {
            for (TodoTask task : todoTasks) {
                if (task.id.equals(taskId)) {
                    TodoTask updatedTask = new TodoTask(task.id, type, description, date, ucId);
                    todoTasks.remove(task);
                    todoTasks.add(updatedTask);
                    return "update task:" + JSONObject.toJSONString(updatedTask);
                }
            }
        }
        return "Task with id: " + taskId + " not found for user with id: " + ucId;
    }


    public record TodoTask(String id, String type, String description, String date, String ucId) {
        // 静态工厂方法，用于创建带有自动生成ID的TodoTask
        public static TodoTask createWithGeneratedId(String type, String description, String date, String ucId) {
            String generatedId = UUID.randomUUID().toString(); // 生成唯一ID
            return new TodoTask(generatedId, type, description, date, ucId);
        }

        public TodoTask {
            if (type == null || type.isBlank()) {
                throw new IllegalArgumentException("Task name cannot be null or blank");
            }
            if (description == null || description.isBlank()) {
                throw new IllegalArgumentException("Task description cannot be null or blank");
            }
            if (date == null || date.isBlank()) {
                throw new IllegalArgumentException("Task date cannot be null or blank");
            }
            if (ucId == null || ucId.isBlank()) {
                throw new IllegalArgumentException("User ID cannot be null or blank");
            }
            if (id == null || id.isBlank()) {
                throw new IllegalArgumentException("ID cannot be null or blank");
            }
        }

    }


}
