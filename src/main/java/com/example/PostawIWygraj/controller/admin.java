package com.example.PostawIWygraj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class admin {
    @RequestMapping(value = "/admin")
    public String getAdmin() {
	return "admin";
    }

}
