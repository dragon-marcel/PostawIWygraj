package com.example.PostawIWygraj.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.PostawIWygraj.component.Helper;
import com.example.PostawIWygraj.model.User;
import com.example.PostawIWygraj.service.UserService;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;
    @Autowired
    private Helper helper;
    @Override
    public boolean supports(Class<?> clazz) {
	return User.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
	User user = (User) o;
	Long id = user.getId();

	if (user.getUsername().toString().isEmpty()) {
	    errors.rejectValue("username", "pole nie może być puste.");
	} else {
	    if (id != null) {
		String oldUserName = userService.findById(id).getUsername();
		if (!oldUserName.equals(user.getUsername()) && userService.findByUsername(user.getUsername()) != null) {
		    errors.rejectValue("username", "Login już istnieje, wybierz inny.");
		}
	    } else {
		if (userService.findByUsername(user.getUsername()) != null) {
		    errors.rejectValue("username", "Login już istnieje, wybierz inny.");
		}
	    }
	}
	if (user.getEmail().toString().isEmpty()) {
	    errors.rejectValue("email", "pole nie może być puste.");
	}else if(!helper.isValidEmailAddress(user.getEmail().toString())) {
	    errors.rejectValue("email", "wprowadz poprawny adres email.");
	} else if (user.getName().toString().isEmpty()) {
	    errors.rejectValue("name", "pole nie może być puste.");
	} else if (user.getSurname().toString().isEmpty()) {
	    errors.rejectValue("surname", "pole nie może być puste.");
	} else if (user.getWorkplace().toString().isEmpty()) {
	    errors.rejectValue("workplace", "pole nie może być puste.");
	}
	if (id == null) {
	    if (user.getPassword().toString().isEmpty()) {
		errors.rejectValue("password", "pole nie może być puste.");
	    } else if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
		errors.rejectValue("password", "Hasło musi mieć od 8 do 32 znaków.");
	    }

	    if (user.getPasswordConfirm().toString().isEmpty()) {
		errors.rejectValue("passwordConfirm", "pole nie może być puste.");
	    } else if (user.getPasswordConfirm() != null && user.getPassword() != null
		    && !user.getPasswordConfirm().equals(user.getPassword())) {
		errors.rejectValue("passwordConfirm", "Hasła muszą być takie same.");
	    }
	}
    }
}
