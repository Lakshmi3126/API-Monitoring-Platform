package com.apimonitor.backend.model;

public class Api {

    private Long id;
    private String name;
    private String url;

    public Api() {
    }

    public Api(Long id, String name, String url){
        this.id = id;
        this.name = name;
        this.url = url;
    }
    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getUrl(){
        return url;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setUrl(String url){
        this.url = url;
    }

}