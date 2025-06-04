package com.czf.ai.lab.server.tools;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

/**
 * 类名：SimpleServer
 * 描述：
 * 作者：zifengchen
 * 日期：2025/4/25
 */
@Service
public class StopOrderTool implements CustomMcpTool{

    private final RestClient restClient;

    @Value("${stop.order.url}")
    private String url;

    public StopOrderTool() {
        this.restClient = RestClient.create();
    }


    @Tool(description = "检索用户停单信息")
    public Object getStopOrderInfo(@ToolParam(description = "用户ucid") String ucId) {
        JSONObject requestBody = constructRequestBody(ucId);
        JSONObject response = restClient.post()
                .uri(url)
                .body(requestBody)
                .retrieve()
                .body(JSONObject.class);

        if (response != null && response.containsKey("data")) {
            return "您的账号被停单的原因是：" + response.getJSONArray("data").toString();
        }
        return "抱歉，未能找到您的停单信息。";
    }


    private JSONObject constructRequestBody(String ucId) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("ucId", ucId);
        return requestBody;
    }

}
