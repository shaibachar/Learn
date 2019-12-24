package com.example.aspect.TestAspect.controller;

import com.example.aspect.TestAspect.services.ApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return applicationService.getHello(name);

    }
}
