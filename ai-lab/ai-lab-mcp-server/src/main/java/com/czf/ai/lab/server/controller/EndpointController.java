package com.czf.ai.lab.server.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * 类名：EndpointController
 * 描述：
 * 作者：zifengchen
 * 日期：2025/5/15
 */
@RestController
public class EndpointController {


    @GetMapping(path = "/api/v1/mcp/messages", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamMessages() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(seq -> "Event " + seq);
    }
}
