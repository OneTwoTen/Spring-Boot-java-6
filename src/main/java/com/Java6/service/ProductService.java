package com.Java6.service;

import java.util.List;

import com.Java6.dto.ProductDto;
import com.Java6.entity.Product;

public interface ProductService {

	List<Product> findAll();

	ProductDto findById(Integer id);

	List<Product> findByCategoryId(String cid);


}
