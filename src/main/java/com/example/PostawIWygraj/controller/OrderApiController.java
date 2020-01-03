package com.example.PostawIWygraj.controller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PostawIWygraj.repository.CustomerRepository;
import com.example.PostawIWygraj.service.ExcelGenerator;


@RestController
@RequestMapping("/api/order")
public class OrderApiController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ExcelGenerator excelGenerator;
    @GetMapping(value = "/raport")
    public ResponseEntity<InputStreamResource> excelReport(@RequestParam String start,@RequestParam String end) throws IOException {
    
    ByteArrayInputStream in = excelGenerator.customersToExcel(start,end);
    String name = "raport"+start+"-"+end +".xlsx";
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "attachment; filename="+name);
    
     return ResponseEntity
                  .ok()
                  .headers(headers)
                  .body(new InputStreamResource(in));
    }
}