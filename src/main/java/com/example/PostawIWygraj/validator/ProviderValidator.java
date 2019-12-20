package com.example.PostawIWygraj.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.PostawIWygraj.model.Provider;
import com.example.PostawIWygraj.service.ProviderService;
@Component
public class ProviderValidator implements Validator{
    @Autowired
    private ProviderService providerService;
    @Override
    public boolean supports(Class<?> clazz) {
	return Provider.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
	Provider provider = (Provider) target;
	Long id = provider.getId();

	if (provider.getName().toString().isEmpty()) {
	    errors.rejectValue("name", "Pole nie może być puste.");
	} else {
	    if (id != null) {
		String oldName = providerService.findById(id).getName().toUpperCase();
		if (oldName != null && !oldName.equals(provider.getName().toUpperCase())
			&& providerService.findByName(provider.getName().toUpperCase()) != null) {
		    errors.rejectValue("name", "Nazwa dostawcy już istnieje, wybierz inną nazwę.");
		}
	    } else {
		if (providerService.findByName(provider.getName().toUpperCase()) != null) {
		    errors.rejectValue("name", "Nazwa dostawcy już istnieje, wybierz inną nazwę.");
		}
	    }
	}
    }

    

}
