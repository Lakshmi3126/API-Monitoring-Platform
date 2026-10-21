package com.apimonitor.backend;

public class ApiNotFoundException extends RuntimeException{

    public ApiNotFoundException(String message) {
        super(message);
    }

}
