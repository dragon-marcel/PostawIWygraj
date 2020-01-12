package com.example.PostawIWygraj.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.PostawIWygraj.model.ErrorApi;
import com.example.PostawIWygraj.model.Order;
import com.example.PostawIWygraj.service.ExcelGenerator;
import com.example.PostawIWygraj.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderApiController {
    @Autowired
    OrderService orderService;
    @Autowired
    ExcelGenerator excelGenerator;

    @GetMapping(value = "/raport")
    public ResponseEntity<?> excelReport(@RequestParam String start, @RequestParam String end,
	    @RequestParam(required = false) Long id) throws IOException {

	ByteArrayInputStream raport = excelGenerator.customersToExcel(start, end, id);
	if (raport != null) {
	    String name = "raport_" + start + "-" + end + ".xlsx";
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Disposition", "attachment; filename=" + name);
	    return ResponseEntity.ok().headers(headers).body(new InputStreamResource(raport));
	} else {

	    ErrorApi error = new ErrorApi("brak danych w raporcie", "error");
	    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

    @PostMapping
    public ResponseEntity<?> addProvider(@RequestBody Order order) {
	orderService.save(order);
	return new ResponseEntity<Order>(order, HttpStatus.OK);

    }
}