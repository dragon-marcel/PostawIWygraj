package com.example.PostawIWygraj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class home {
    @RequestMapping(value = "/home")
    public String getHome() {
	return "home";
    }

}
