package com.example.PostawIWygraj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.PostawIWygraj.model.User;
import com.example.PostawIWygraj.service.UserServiceImpl;

@Controller
public class user {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value = "/user")
    public String getUser() {
	return "user";
    }

}
