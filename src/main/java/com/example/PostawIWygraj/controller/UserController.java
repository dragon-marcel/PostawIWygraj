package com.example.PostawIWygraj.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.PostawIWygraj.model.User;
import com.example.PostawIWygraj.service.SecurityService;
import com.example.PostawIWygraj.service.UserService;
import com.example.PostawIWygraj.validator.UserValidator;

@Controller
@SessionAttributes({"nameUser","countLoggedUsers","loggedUsers"})

public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;

    @Autowired
    private SecurityService securityService;

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

    @GetMapping(value = "/registration")
    public String registry(Model model) {
	model.addAttribute("userForm", new User());
	return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
	userValidator.validate(userForm, bindingResult);

	if (bindingResult.hasErrors()) {
	    return "registration";
	}
	userService.save(userForm);
	securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

	return "redirect:/index";
    }
    
    
    @GetMapping("/userForm")
    public String newUser(Model model){
	String nameUser = securityService.getNameLoogedUser();
	model.addAttribute("nameUser",nameUser);
	model.addAttribute("countLoggedUsers",userService.getCountCurrentLoggedUsers());
	model.addAttribute("loggedUsers",userService.getCurrentLoggedUsers());
	User user = new User();
         model.addAttribute("userForm",user);

         return "userForm";
    }
    
    
    @GetMapping("/userForm/{id}")
    public String newUser(@PathVariable Long id,Model model){
	String nameUser = securityService.getNameLoogedUser();
	model.addAttribute("nameUser",nameUser);
	model.addAttribute("countLoggedUsers",userService.getCountCurrentLoggedUsers());
	model.addAttribute("loggedUsers",userService.getCurrentLoggedUsers());
	User user = userService.findById(id);
         model.addAttribute("userForm",user);

         return "userForm";
    }
    
    @PostMapping("/userForm")
    public String editUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,RedirectAttributes flash) {
	userValidator.validate(userForm, bindingResult);
	if (bindingResult.hasErrors()) {
	    System.out.print("DODAJE"+userForm.getName());

	     return "userForm";
	}
	flash.addFlashAttribute("success", "Uzytkownik zostął pomyślnie zaktualizowany.");
	userService.editUser(userForm);
	return "redirect:/userForm/"+userForm.getId();
    }
    
    @RequestMapping(value = "/users")
    public String findAllUsers(Model model) {
	String nameUser = securityService.getNameLoogedUser();
	model.addAttribute("nameUser",nameUser);
	model.addAttribute("countLoggedUsers",userService.getCountCurrentLoggedUsers());
	model.addAttribute("loggedUsers",userService.getCurrentLoggedUsers());
	model.addAttribute("userId",securityService.getIdLoogerUser());
	List<User> users = userService.findAll();
	model.addAttribute("users", users);
	return "users";
    }

    
    



}
