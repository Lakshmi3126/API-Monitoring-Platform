package com.apimonitor.backend.service;
import com.apimonitor.backend.model.Api;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class ApiService {
    private List<Api> apis = new ArrayList<>();

    public List<Api> getApis(){
        return apis;
    }

    public Api addApi(Api api){
        apis.add(api);
        return api;
    }

}
