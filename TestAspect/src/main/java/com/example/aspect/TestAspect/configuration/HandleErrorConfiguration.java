package com.example.aspect.TestAspect.configuration;

import com.example.aspect.TestAspect.aspects.ApplicationErrorAspect;
import com.example.aspect.TestAspect.services.ApplicationHandleError;
import com.example.aspect.TestAspect.services.ApplicationHandleErrorImpl;
import org.aspectj.lang.Aspects;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class HandleErrorConfiguration {

    @Bean
    public ApplicationHandleError getApplicationHandleError() {
        return new ApplicationHandleErrorImpl();
    }

    @Bean
    public ApplicationErrorAspect getApplicationErrorAspect() {
        return Aspects.aspectOf(ApplicationErrorAspect.class);
    }
}
