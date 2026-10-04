package com.apimonitor.backend.controller;

import com.apimonitor.backend.model.Api;
import com.apimonitor.backend.service.ApiService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/api/{id}")
    public Api getApi(@PathVariable("id") Long apiId){
        Api resApi = service.getApi(apiId);
        if (resApi==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "API not found");
        else return resApi;
    }

    @PutMapping("/api/{id}")
    public Api updateApi(@PathVariable("id") Long apiId, @RequestBody Api api){
        Api resApi = service.updateApi(apiId,api);
        if(resApi==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "API not found");
        return resApi;
    }

    @DeleteMapping("/api/{id}")
    public String deleteApi(@PathVariable("id") Long apiId){
        Boolean deleted = service.deleteApi(apiId);
        if(deleted) return "API deleted";
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "API not found");
    }

}