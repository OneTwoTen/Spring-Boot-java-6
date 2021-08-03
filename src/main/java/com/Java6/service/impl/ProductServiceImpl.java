package com.Java6.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Java6.JPA.ProductRepository;
import com.Java6.Util.ObjectMapperUtils;
import com.Java6.dto.ProductDto;
import com.Java6.entity.Product;
import com.Java6.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{


	@Autowired
	ProductRepository productRepo;
	@Autowired
	private ObjectMapperUtils objectMapper;
	@Override
	public ProductDto findById(Integer id) {
		Product product =productRepo.findById(id).get();
		ProductDto result = objectMapper.convertEntityAndDto(product, ProductDto.class);
		return result;
	}
	@Override
	public List<Product> findAll() {
		
		return productRepo.findAll();
	}
	@Override
	public List<Product> findByCategoryId(String cid) {
		return productRepo.findByCategoryId(cid);
	}


}
