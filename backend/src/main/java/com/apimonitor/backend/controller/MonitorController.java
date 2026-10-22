package com.apimonitor.backend.controller;

import com.apimonitor.backend.model.Api;
import com.apimonitor.backend.service.ApiService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RestController
@Validated
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
    public Api addApi(@Valid @RequestBody Api api){
        return service.addApi(api);
    }

    @GetMapping("/api/{id}")
    public Api getApi(@PathVariable("id") Long apiId){
        Api resApi = service.getApi(apiId);
        return resApi;
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

    @GetMapping("/api/search")
    public List<Api> searchApis(@RequestParam String name) {
        return service.matchingApis(name);
    }

    @GetMapping("/api/paginated")
    public Page<Api> pageApis(@RequestParam(name="page", defaultValue="0") @Min(0) int pageNo, @RequestParam(name="size", defaultValue="10") @Min(1) int size, @RequestParam(defaultValue="name") String sortBy, @RequestParam(name="direction", defaultValue="asc") String direc){
        if(!sortBy.equals("id") && !sortBy.equals("name") && !sortBy.equals("url")) throw new IllegalArgumentException("Invalid sort field");
    return service.paginateApis(pageNo,size,sortBy, direc);
    }

}