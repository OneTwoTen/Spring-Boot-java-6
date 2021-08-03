package com.Java6.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Java6.Util.ObjectMapperUtils;
import com.Java6.dto.ProductDto;
import com.Java6.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/rest/products/")
public class ProductRestController {

	@Autowired
	ProductService productService;
	@Autowired
	private ObjectMapperUtils objectMapper;
// 	@GetMapping(value = "{id}")
// 	public Product getOne(@PathVariable("id") String id) {
// 		return productService.findById(Integer.parseInt(id));
// 	}

	@GetMapping(value="/{id}", produces = "application/json")
	public ResponseEntity<ProductDto> getOne(@PathVariable("id") Integer id) {
		ProductDto productDto = productService.findById(id);
		System.out.println("pro: "+productDto.toString());
		return ResponseEntity.ok(productDto);
	}
}
