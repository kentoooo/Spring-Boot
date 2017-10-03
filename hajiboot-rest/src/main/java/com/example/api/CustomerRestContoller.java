package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.example.domain.Customer;
import com.example.service.CustomerService;

@RestController
@RequestMapping("api/customers")
public class CustomerRestContoller {
	@Autowired
	CustomerService customerService;
	
	// 顧客全件取得
	@GetMapping
	List<Customer> getCustomers(@PageableDefault Pageable pageable) {
		List<Customer> customers = customerService.findAll(pageable);
		return customers;
	}
	
	// 顧客一件取得
	@GetMapping(path = "{id}")
	Customer getCustomer(@PathVariable Integer id) {
		Customer customer = customerService.findOne(id);
		return customer;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	Customer postCustomers(@RequestBody Customer customer) {
		return customerService.create(customer);
	}
	
	@PutMapping(path = "{id}")
	Customer putCustomer(@PathVariable Integer id, @RequestBody Customer customer){
		customer.setId(id);
		return customerService.update(customer);
	}
	
	@DeleteMapping(path = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteCustomer(@PathVariable Integer id) {
		customerService.delete(id);
	}
	
}
