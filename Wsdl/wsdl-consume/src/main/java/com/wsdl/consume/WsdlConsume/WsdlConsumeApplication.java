package com.wsdl.consume.WsdlConsume;

import com.wsdl.consume.WsdlConsume.component.CountryClient;
import com.wsdl.consume.WsdlConsume.wsdl.GetCountryResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WsdlConsumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsdlConsumeApplication.class, args);
    }

    @Bean
    CommandLineRunner lookup(CountryClient quoteClient) {
        return args -> {
            String country = "Poland";

            if (args.length > 0) {
                country = args[0];
            }
            GetCountryResponse response = quoteClient.getCountry(country);
            System.err.println(response.getCountry().getCurrency());
        };
    }

}

