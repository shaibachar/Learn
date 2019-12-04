package com.firebaseTest.services;

import com.google.firebase.auth.FirebaseAuth;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FireBaseTestService {

    public String getUserId(String idToken) throws InterruptedException, ExecutionException {
        String uid;
        FirebaseAuth instance = FirebaseAuth.getInstance();
        uid = instance.verifyIdTokenAsync(idToken).get().getUid();
        return uid;
    }

}
