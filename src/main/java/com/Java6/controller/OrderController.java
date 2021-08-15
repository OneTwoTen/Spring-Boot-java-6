package com.Java6.controller;

import javax.servlet.http.HttpServletRequest;

import com.Java6.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/order/")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping(value = "checkout")
	public String checkout() {
		return "order/checkout";
	}
	@GetMapping(value = "list")
	public String list(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
		return "order/list";
	}
	@GetMapping(value = "detail/{id}")
	// @ResponseBody
	public String detail(@PathVariable("id") long id, Model model) {
		model.addAttribute("order", orderService.findById(id));
		// System.out.println( orderService.findById(id));
		return "order/detail";
		// return "null";
	}
	
	
}
