package com.czf.ai.lab.controller;

import com.czf.ai.lab.client.SimpleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




/**
 * 类名：TestContoller
 * 描述：
 * 作者：zifengchen
 * 日期：2025/5/10
 */
@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @Autowired
    private SimpleClient simpleClient;


    @RequestMapping("/chat")
    public String chat(@RequestParam String query) {
        return simpleClient.chat(query);
    }

    @RequestMapping("/toolList")
    public Object test() {
        return simpleClient.tools();
    }

}
