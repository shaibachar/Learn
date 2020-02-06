package com.example.ImageJ.ImageJ.controller;

import com.example.ImageJ.ImageJ.services.ApplicationService;
import com.example.ImageJ.ImageJ.services.util.GeneralUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RestController
public class ApplicationController {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return applicationService.getHello(name);
    }

    @PostMapping("/uploadOCR")
    public ResponseEntity<String> uploadOCR(@RequestPart("file") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            InputStream inputStream = new ByteArrayInputStream(bytes);
            BufferedImage imBuff = ImageIO.read(inputStream);
            String result = applicationService.doOcr(imBuff);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            logger.error("Error while loading image ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/uploadImageRightOCR")
    public ResponseEntity<String> uploadImageRightOCR(@RequestPart("file") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            InputStream inputStream = new ByteArrayInputStream(bytes);
            String result = applicationService.doOcrRightUpperCorner(inputStream);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            logger.error("Error while loading image ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/uploadImageLeftOCR")
    public ResponseEntity<String> uploadImageLeftOCR(@RequestPart("file") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            InputStream inputStream = new ByteArrayInputStream(bytes);
            String result = applicationService.doOcrLeftUpperCorner(inputStream);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            logger.error("Error while loading image ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/uploadImage/{partsCount}")
    public ResponseEntity<String> uploadImage(@RequestPart("file") MultipartFile file, @PathVariable("partsCount") Integer partsCount) {
        try {
            String fileName = file.getOriginalFilename();
            byte[] bytes = file.getBytes();
            BufferedImage bufferedImage = GeneralUtils.byteArrayToBufferedImage(bytes);
            BufferedImage result = applicationService.processRightUpperCornerImage(bufferedImage, partsCount);
            return ResponseEntity.ok().body("success");
        } catch (Exception e) {
            logger.error("Error while loading image ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
