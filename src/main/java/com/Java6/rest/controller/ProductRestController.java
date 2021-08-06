package com.Java6.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.Java6.JPA.ProductRepository;
import com.Java6.Util.ObjectMapperUtils;
import com.Java6.dto.ProductDto;
import com.Java6.entity.Product;
import com.Java6.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/rest/products/")
public class ProductRestController {

	@Autowired
	ProductService productService;

	@Autowired
	ProductRepository productRepository;
	@Autowired
	private ObjectMapperUtils objectMapper;
	// @GetMapping(value = "{id}")
	// public Product getOne(@PathVariable("id") String id) {
	// return productService.findById(Integer.parseInt(id));
	// }

	@GetMapping(value="")
    public ResponseEntity<List<ProductDto>> getCategory(
        @RequestParam(defaultValue="0") Integer pageIndex,
        @RequestParam(defaultValue="5") Integer pageSize,
        @RequestParam(defaultValue="id") String sortBy,
        @RequestParam(defaultValue="ASC") String sortDirection
    ){
        List<ProductDto> list = productService.findAll(sortDirection, sortBy, pageIndex, pageSize);
        if(list.isEmpty())
        return ResponseEntity.noContent().build();
        return ResponseEntity.ok(list);
    }

	@GetMapping(value = "/getOne/{id}", produces = "application/json")
	public ResponseEntity<ProductDto> getOne(@PathVariable("id") Integer id) {
		ProductDto productDto = productService.findById(id);
		System.out.println("pro: " + productDto.getCategoryId());
		return ResponseEntity.ok(productDto);
	}

	@GetMapping(value = "/get/{id}", produces = "application/json")
	public ResponseEntity<Product> get(@PathVariable("id") Integer id) {
		Product product = productService.findPro(id);
		return ResponseEntity.ok(product);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<ProductDto> deleteOne(@PathVariable("id") Integer id) {
		if (!productRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		if (productService.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@PostMapping(value = "create")
	public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto
			) {
		// if (productDto.getId() != null) {
		// 	return ResponseEntity.ok(productService.update(productDto, categoryId));
		// }
		return ResponseEntity.ok(productService.create(productDto));
	}

	@PutMapping(value = "update/{id}")
	public ResponseEntity<ProductDto> update(ProductDto productDto, @PathVariable("id") Integer id,
			@RequestParam(value = "categoryId") String categoryId) {
		if (id != null)
			return ResponseEntity.ok(productService.update(productDto, categoryId));
		return ResponseEntity.badRequest().build();
	}
}
