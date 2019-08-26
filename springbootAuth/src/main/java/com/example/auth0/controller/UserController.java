package com.example.auth0.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth0.domain.ApplicationUser;
import com.example.auth0.repository.ApplicationUserRepository;
import com.example.auth0.security.JwtAuthentication;

@RestController
@RequestMapping("/users")
public class UserController {

    private ApplicationUserRepository applicationUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (applicationUserRepository.findByUsername(user.getUsername()) == null) {
            applicationUserRepository.save(user);
        }
    }

    @GetMapping("/userProperties")
    public List<String> getUserProperties() {
        List<String> res = new ArrayList<>();
        JwtAuthentication authentication = (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
        Properties properties = authentication.getProperties();
        for (Object key : properties.keySet()) {
            Object value = properties.get(key);
            res.add(MessageFormat.format("key:{0}  value:{1}", key, value));
        }
        return res;
    }
}