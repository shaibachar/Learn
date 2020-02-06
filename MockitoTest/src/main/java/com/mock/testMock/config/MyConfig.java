package com.mock.testMock.config;

import com.mock.testMock.service.IMyService;
import com.mock.testMock.service.MyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public IMyService getMyService(){
        return new MyService();
    }
}
