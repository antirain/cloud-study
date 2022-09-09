package com.hyf.cloud.controller;

import com.hyf.cloud.annotation.NotControllerResponseAdvice;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
@Async
@EventListener
    @GetMapping("/health")
    @NotControllerResponseAdvice
    public String health() {
        return "success";
    }

}