package com.apimonitor.backend.service;
import com.apimonitor.backend.model.Api;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ApiService {
    private final List<Api> apis = new ArrayList<>();

    public List<Api> getApis(){
        return apis;
    }

    public Api addApi(Api api){
        apis.add(api);
        return api;
    }

    public Api getApi(Long ApiId){
        for(Api api : apis){
            if(Objects.equals(api.getId(), ApiId)){
                return api;
            }
        }
        return null;
    }

    public Api updateApi(Long apiId, Api newApi){
        for(Api api :apis){
            if(Objects.equals(api.getId(), apiId)){
                api.setUrl(newApi.getUrl());
                api.setName(newApi.getName());
                return api;
            }
        }
        return null;
    }

}
