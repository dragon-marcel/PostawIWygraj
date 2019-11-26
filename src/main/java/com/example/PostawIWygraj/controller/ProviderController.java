package com.example.PostawIWygraj.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.PostawIWygraj.model.Provider;
import com.example.PostawIWygraj.service.ProviderService;

@Controller
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping(value = "/providers")
    public String getAllProviders(Model model) {

	List<Provider> provider = providerService.findAll();
	model.addAttribute("providers", provider);
	return "providers";
    }
}
