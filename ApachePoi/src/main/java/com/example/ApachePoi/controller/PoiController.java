package com.example.ApachePoi.controller;

import com.example.ApachePoi.service.ExcelServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PoiController {

    private final ExcelServices excelServices;

    public PoiController(ExcelServices excelServices) {
        this.excelServices = excelServices;
    }

    @PostMapping("/trimFile/{filePath}")
    public ResponseEntity trimFile(@PathVariable String filePath) {
        try {
            excelServices.trimExistingWorkbook(filePath);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok("ok");
        //"existing-spreadsheet.xlsx"
    }

    @PostMapping("/createFile/{filePath}")
    public ResponseEntity createFile(@PathVariable String filePath) {
        try {
            excelServices.create(filePath);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok("ok");
        //"existing-spreadsheet.xlsx"
    }

    @PostMapping("/addEntry/{filePath}")
    public ResponseEntity addEntry(@PathVariable String filePath) {
        try {
            excelServices.modifyExistingWorkbook(filePath);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok("ok");
        //"existing-spreadsheet.xlsx"
    }
}
