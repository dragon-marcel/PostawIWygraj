package com.example.PostawIWygraj.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.PostawIWygraj.model.Customer;
import com.example.PostawIWygraj.service.CustomerService;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/customers")
    public String getAllCustomers(Model model) {

	List<Customer> customers = customerService.findAll();
	model.addAttribute("customers", customers);
	return "customers";
    }
}
