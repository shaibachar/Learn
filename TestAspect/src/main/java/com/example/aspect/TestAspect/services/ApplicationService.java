package com.example.aspect.TestAspect.services;

import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    public String getHello(String name) {
//        return String.format("hello %s", name);
        throw new RuntimeException("error");
    }
}
