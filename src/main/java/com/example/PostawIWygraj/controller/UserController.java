package com.example.PostawIWygraj.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.PostawIWygraj.model.User;
import com.example.PostawIWygraj.model.UserPrincipal;
import com.example.PostawIWygraj.service.FileService;
import com.example.PostawIWygraj.service.UserService;
import com.example.PostawIWygraj.validator.UserValidator;

@Controller
@SessionAttributes({ "nameUser", "countLoggedUsers", "loggedUsers" })

public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private FileService fileService;
	// private UserValidator userValidator;

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

	@GetMapping("/user/{id}")
	public String newUser(@PathVariable Long id, Model model) {
		model.addAttribute("countLoggedUsers", userService.getCountCurrentLoggedUsers());
		model.addAttribute("loggedUsers", userService.getCurrentLoggedUsers());
		User user = userService.findById(id);
		model.addAttribute("userForm", user);

		return "userForm";
	}

	@PostMapping("/users")
	public String saveOrUpdateUser(@ModelAttribute("userForm") User user,
			BindingResult bindingResult, RedirectAttributes flash) {
//	userValidator.validate(user, bindingResult);
//	if (bindingResult.hasErrors()) {
//	    System.out.print("DODAJE"+user.getName());
//	    userService.save(user);
//	     return "users";
//	}
		
		flash.addFlashAttribute("success", "Uzytkownik zostął pomyślnie dodany.");
		userService.save(user);
		return "redirect:/users";
	}

	@RequestMapping(value = "/users")
	public String findAllUsers(Model model) {
		List<User> users = userService.findAll();
		User user = new User();
		model.addAttribute("userForm", user);
		model.addAttribute("users", users);
		return "users";
	}

}
