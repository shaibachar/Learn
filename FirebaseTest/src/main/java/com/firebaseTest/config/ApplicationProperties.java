package com.firebaseTest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private String fireBase_base_url;

    public String getFireBase_base_url() {
        return fireBase_base_url;
    }

    public void setFireBase_base_url(String fireBase_base_url) {
        this.fireBase_base_url = fireBase_base_url;
    }
}
