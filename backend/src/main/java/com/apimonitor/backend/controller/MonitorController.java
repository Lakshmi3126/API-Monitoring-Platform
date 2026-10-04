package com.apimonitor.backend.controller;

import com.apimonitor.backend.model.Api;
import com.apimonitor.backend.service.ApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MonitorController {

    private final ApiService service;

    public MonitorController(ApiService service) {
        this.service = service;
    }

    @GetMapping("/api/health")
    public String health() {
        return "API Monitoring Backend is running!";
    }

    @GetMapping("/api")
    public List<Api> getApis(){
        return service.getApis();
    }

    @PostMapping("/api")
    public Api addApi(@RequestBody Api api){
        return service.addApi(api);
    }
}