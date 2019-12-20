package com.example.PostawIWygraj.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.PostawIWygraj.component.Helper;
import com.example.PostawIWygraj.model.Customer;
import com.example.PostawIWygraj.model.User;
import com.example.PostawIWygraj.service.CustomerService;

@Component
public class CustomerValidator implements Validator {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private Helper helper;

    @Override
    public boolean supports(Class<?> clazz) {
	return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
	Customer customer = (Customer) target;
	Long id = customer.getId();
	if (customer.getName().toString().isEmpty()) {
	    errors.rejectValue("name", "Pole nie może być puste.");
	} else {
	    if (id != null) {
		String oldName = customerService.findById(id).getName().toUpperCase();
		if (oldName != null && !oldName.equals(customer.getName().toUpperCase())
			&& customerService.findByName(customer.getName().toUpperCase()) != null) {
		    errors.rejectValue("name", "Nazwa klienta już istnieje, wybierz inny.");
		}
	    } else {
		if (customerService.findByName(customer.getName().toUpperCase()) != null) {
		    errors.rejectValue("name", "Login już istnieje, wybierz inny.");
		}
	    }
	}

	if (customer.getEmail().toString().isEmpty()) {
	    errors.rejectValue("email", "Pole nie może być puste.");
	} else if (!helper.isValidEmailAddress(customer.getEmail().toString())) {
	    errors.rejectValue("email", "wprowadz poprawny adres email.");
	}

	if (customer.getNrTel().toString().isEmpty()) {
	    errors.rejectValue("nrTel", "Pole nie może być puste.");
	}
    }

}
