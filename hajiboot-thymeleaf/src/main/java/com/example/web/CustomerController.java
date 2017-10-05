package com.example.web;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import com.example.domain.Customer;
import com.example.service.CustomerService;

@Controller
@RequestMapping("customers")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	@ModelAttribute
	CustomerForm setUpForm() {
		return new CustomerForm();
	}
	
	@GetMapping
	String list(Model model) {
		List<Customer> customers = customerService.findAll();
		model.addAttribute("customers",customers);
		return "customers/list";
	}
	
	@PostMapping(path = "create")
	String create(@Validated CustomerForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return list(model);
		}
		Customer customer = new Customer();
		BeanUtils.copyProperties(form, customer);
		customerService.create(customer);
		return "redirect:/customers";
	}

}
