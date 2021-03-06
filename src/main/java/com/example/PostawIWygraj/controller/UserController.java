package com.example.PostawIWygraj.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @RequestMapping(value = "/login")
    public String login(RedirectAttributes flash, Principal principal,
	    @RequestParam(value = "error", required = false) String error,
	    @RequestParam(value = "logout", required = false) String logout) {

	if (principal != null) {
	    flash.addFlashAttribute("success", "success");
	    return "redirect:/index";
	}
	if (error != null) {
	    flash.addFlashAttribute("danger", "Twoj login lub hasło jest niepoprawne !!");
	    return "redirect:/login";
	}
	if (logout != null) {
	    flash.addFlashAttribute("success", "Zostałeś wylogowany.");
	    return "redirect:/login";
	}

	return "login";
    }

    @RequestMapping(value = "/users")
    public String findAllUsers(Model model) {
	return "users";
    }

}
