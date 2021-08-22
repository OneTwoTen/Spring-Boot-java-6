package com.Java6.service;

import java.util.List;

import com.Java6.dto.ProductDto;
import com.Java6.entity.Product;

public interface ProductService extends BaseService<Product, ProductDto> {

	List<Product> findAll();

	List<ProductDto> findAll(String name, String sortDirection, String sortBy, int pageIndex, int pageSize);
	
	ProductDto findById(Integer id);

	List<Product> findByCategoryId(String cid);
	
	ProductDto create(ProductDto cDto);

	ProductDto update(ProductDto cDto, String categoryId);

}
