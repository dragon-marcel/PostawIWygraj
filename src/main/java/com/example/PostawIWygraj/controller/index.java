package com.example.PostawIWygraj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.PostawIWygraj.service.SecurityService;
import com.example.PostawIWygraj.service.UserService;
import com.example.PostawIWygraj.validator.UserValidator;

@Controller
public class index {
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;

    @GetMapping(value = "/index")
    public String getHome(Model model) {
	String nameUser = securityService.getNameLoogedUser();
	model.addAttribute("nameUser",nameUser);
	model.addAttribute("countLoggedUsers",userService.getCountCurrentLoggedUsers());
	model.addAttribute("loggedUsers",userService.getCurrentLoggedUsers());

	return "index";
    }


}
