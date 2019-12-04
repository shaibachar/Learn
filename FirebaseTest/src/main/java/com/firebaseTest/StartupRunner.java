package com.firebaseTest;

import com.firebaseTest.config.ApplicationProperties;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@ConditionalOnProperty(prefix = "job.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
public class StartupRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(StartupRunner.class);

    @Autowired
    private ApplicationProperties applicationProperties;

    @Override
    public void run(String... args) throws Exception {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource("/firebase-authentication.json").getInputStream()))
                    .setDatabaseUrl(applicationProperties.getFireBase_base_url())
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            log.error("error while authenticate to firebase");
        }
    }
}
