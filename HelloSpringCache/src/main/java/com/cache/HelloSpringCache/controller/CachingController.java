package com.cache.HelloSpringCache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cache.HelloSpringCache.service.CachingService;

@RestController
public class CachingController {
     
    @Autowired
    private CachingService cachingService;
     
    @GetMapping("clearAllCaches")
    public void clearAllCaches() {
        cachingService.evictAllCaches();
    }
}

