package com.example.PostawIWygraj.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.PostawIWygraj.model.Assortment;
import com.example.PostawIWygraj.service.AssortmentService;

@Controller
public class AssortmentController {
    
    @Autowired
    private AssortmentService assortmentService;

    @GetMapping(value = "/assortments")
    public String getAllAssortments(Model model) {

	List<Assortment> assortments = assortmentService.findAll();
	model.addAttribute("assortments", assortments);
	return "assortments";
    }

}
