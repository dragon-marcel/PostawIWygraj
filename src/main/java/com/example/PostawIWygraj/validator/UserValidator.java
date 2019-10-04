package com.example.PostawIWygraj.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.example.PostawIWygraj.model.User;
import com.example.PostawIWygraj.service.UserService;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
	return User.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
	User user = (User) o;
	Long id = user.getId();

	if (id != null) {
	    String oldUserName = userService.findById(id).getUsername();
	    if (!oldUserName.equals(user.getUsername()) && userService.findByUsername(user.getUsername()) != null) {
		errors.rejectValue("username", "Validator.userForm.username.duplicate");
	    }
	} else {
	    if (userService.findByUsername(user.getUsername()) != null) {
		errors.rejectValue("username", "Validator.userForm.username.duplicate");
	    }
	
	if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
	    errors.rejectValue("password", "Validator.userForm.password.size");
	}

	if (!user.getPasswordConfirm().equals(user.getPassword())) {
	    errors.rejectValue("passwordConfirm", "Validator.userForm.passwordConfirm.diff");
	}
	}
    }

}
