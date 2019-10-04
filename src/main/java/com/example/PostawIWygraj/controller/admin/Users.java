package com.example.PostawIWygraj.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.PostawIWygraj.model.User;
import com.example.PostawIWygraj.service.UserService;


@Controller
@RequestMapping(value = "/admin")
public class Users {
    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/users")
    public String findAllUsers(Model model) {

	List<User> users = userService.findAll();
	model.addAttribute("users", users);
	return "users";
    }

}
