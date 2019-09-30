package com.example.PostawIWygraj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class error_403 {
    @RequestMapping(value = "/error_403")
    public String getError403() {
	return "error_403";
    }

}
