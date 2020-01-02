package com.example.PostawIWygraj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PostawIWygraj.model.ChartOrder;
import com.example.PostawIWygraj.service.ChartOrderService;

@RestController
@RequestMapping(value = "/api/charts")
public class ChartOrderApiController {
    @Autowired
    private ChartOrderService chartOrderService;

    @GetMapping
    public ResponseEntity<List<ChartOrder>> getChartOrder() {
	List<ChartOrder> orders = chartOrderService.getChartOrderLasWeek();
	return new ResponseEntity<List<ChartOrder>>(orders, HttpStatus.OK);
    }
}
