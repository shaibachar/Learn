package com.firebaseTest.controller;

import com.firebaseTest.services.FireBaseTestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class FireBaseTestController {

    private final FireBaseTestService fireBaseTestService;

    public FireBaseTestController(FireBaseTestService fireBaseTestService) {
        this.fireBaseTestService = fireBaseTestService;
    }

    @GetMapping("/validateToken/{token}")
    public ResponseEntity validateToken(@PathVariable String idToken) {
        String uid = null;
        try {
            uid = fireBaseTestService.getUserId(idToken);
        } catch (InterruptedException | ExecutionException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User Not Authenticated");
        }
        return ResponseEntity.status(HttpStatus.OK).body(uid);
    }

}
