package com.apimonitor.backend.service;
import com.apimonitor.backend.model.Api;
import com.apimonitor.backend.repository.ApiRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ApiService {

    ApiRepository apiRepository;

    public ApiService(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    public List<Api> getApis(){
        return apiRepository.findAll();
    }

    public Api addApi(Api api){
        return apiRepository.save(api);
    }

    public Api getApi(Long ApiId){
        Optional<Api> apiOptional = apiRepository.findById(ApiId);
        return apiOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "API not found"));
    }

    public Api updateApi(Long apiId, Api newApi){
        newApi.setId(apiId);
        return apiRepository.save(newApi);
    }

    public Boolean deleteApi(Long apiId){
        Boolean flag = apiRepository.existsById(apiId);
        apiRepository.deleteById(apiId);
        return flag;
    }

}
