package com.mock.testMock.controller;

import com.mock.testMock.service.IMyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    private final IMyService iMyService;

    public MyController(IMyService iMyService) {
        this.iMyService = iMyService;
    }

    @GetMapping("/getCalculation/{x}/{y}")
    public ResponseEntity<Integer> getCalculation(@PathVariable("x") Integer x, @PathVariable("y") Integer y) {
        try {
            int calculate = iMyService.calculate(x, y);
            return ResponseEntity.ok().body(calculate);
        } catch (Exception e) {
            logger.error("Error while loading image ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
