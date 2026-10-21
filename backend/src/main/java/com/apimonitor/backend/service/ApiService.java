package com.apimonitor.backend.service;
import com.apimonitor.backend.ApiNotFoundException;
import com.apimonitor.backend.model.Api;
import com.apimonitor.backend.repository.ApiRepository;
import org.springframework.stereotype.Service;

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

    public Api getApi(Long apiId){
        Optional<Api> apiOptional = apiRepository.findById(apiId);
        return apiOptional.orElseThrow(() -> new ApiNotFoundException("API ID " +apiId+" not found"));
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

    public List<Api> matchingApis(String name){
        return apiRepository.findByNameContaining(name);
    }

}
