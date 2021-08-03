package com.Java6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/order/")
public class OrderController {

	@GetMapping(value = "checkout")
	public String checkout() {
		return "order/checkout";
	}
	@GetMapping(value = "list")
	public String list() {
		return "order/list";
	}
	@GetMapping(value = "detail/{id}")
	public String detail() {
		return "order/detail";
	}
	
	
}
