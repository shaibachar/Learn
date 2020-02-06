package com.wsdl.consume.WsdlConsume.component;

import com.wsdl.consume.WsdlConsume.wsdl.GetCountryRequest;
import com.wsdl.consume.WsdlConsume.wsdl.GetCountryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;


public class CountryClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(CountryClient.class);

    public GetCountryResponse getCountry(String country) {

        GetCountryRequest request = new GetCountryRequest();
        request.setName(country);

        log.info("Requesting location for " + country);

        GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8081/ws", request,
                        new SoapActionCallback(
                                "http://localhost:8081/ws"));

        return response;
    }

}