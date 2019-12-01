package com.hello.HelloRestAssured.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hello.HelloRestAssured.domain.Customer;
import com.hello.HelloRestAssured.service.CustomerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.hello.HelloRestAssured.service.HelloService;

@Profile("test")
@Configuration
public class TestApplicationConfig {

	private static Log logger = LogFactory.getLog(TestApplicationConfig.class);
	
	@Bean
	protected ServletContextListener listener() {
		return new ServletContextListener() {

			@Override
			public void contextInitialized(ServletContextEvent sce) {
				logger.info("ServletContext initialized");
			}

			@Override
			public void contextDestroyed(ServletContextEvent sce) {
				logger.info("ServletContext destroyed");
			}

		};
	}

	@Bean
	@Primary
	public HelloService HelloService() {
		return Mockito.mock(HelloService.class);
	}

	@Bean
	@Primary
	public CustomerService CustomerService() {
		return Mockito.mock(CustomerService.class);
	}
}
