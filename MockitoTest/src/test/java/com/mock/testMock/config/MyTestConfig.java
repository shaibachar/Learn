package com.mock.testMock.config;

import com.mock.testMock.service.MyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.mock;

@Configuration
@Import({MyConfig.class})
public class MyTestConfig {


}
