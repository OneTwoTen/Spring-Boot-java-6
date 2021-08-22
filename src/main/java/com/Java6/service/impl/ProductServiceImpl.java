package com.Java6.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Java6.JPA.CategoryRepository;
import com.Java6.JPA.ProductRepository;
import com.Java6.Util.ObjectMapperUtils;
import com.Java6.Util.PageableUtils;
import com.Java6.dto.CategoryDto;
import com.Java6.dto.ProductDto;
import com.Java6.entity.Product;
import com.Java6.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepo;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	private ObjectMapperUtils objectMapper;

	@Override
	public ProductDto findById(Integer id) {
		Product product = productRepo.findById(id).get();
		product.setCategory(categoryRepository.getById(product.getCategory().getId()));
		ProductDto result = objectMapper.convertEntityAndDto(product, ProductDto.class);
		// CategoryDto categoryDto =
		// objectMapper.convertEntityAndDto(product.getCategory(), CategoryDto.class);
		// result.setCategoryDto(categoryDto);
		return result;
	}

	public Integer getTotalProductCount() {
		return productRepo.getTotalProductCount();
	}

	@Override
	public List<Product> findAll() {

		return productRepo.findAll();
	}

	@Override
	public List<Product> findByCategoryId(String cid) {
		return productRepo.findByCategoryId(cid);
	}

	@Override
	public List<ProductDto> findAll(String name, String sortDirection, String sortBy, int pageIndex, int pageSize) {
		Pageable pageable = PageableUtils.pageableUtils(sortDirection, sortBy, pageIndex, pageSize);
		List<Product> list = productRepo.findAll("%" + name + "%", pageable);
		List<ProductDto> listdDto = objectMapper.mapAll(list, ProductDto.class);
		return listdDto;
	}

	@Override
	public boolean delete(Integer id) {
		Product product = productRepo.getById(id);
		if (product != null) {
			productRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ProductDto create(ProductDto cDto) {
		Product product = objectMapper.convertEntityAndDto(cDto, Product.class);
		// product.setCategory(categoryRepository.findById(cDto.getCategory().getId()));
		productRepo.save(product);
		System.out.println(cDto);
		cDto.setId(product.getId());
		System.out.println(cDto);
		return cDto;
	}

	@Override
	public ProductDto update(ProductDto cDto) {
		// TODO Auto-generated method stub
		return null;
	}
	// @Override
	// public ProductDto create(ProductDto cDto, String categoryId) {

	// Product product = objectMapper.convertEntityAndDto(cDto, Product.class);
	// product.setCategory(categoryRepository.getById(categoryId));
	// productRepo.save(product);
	// cDto.setId(product.getId());
	// return cDto;
	// }
	@Override
	public ProductDto update(ProductDto cDto, String categoryId) {

		Product product = objectMapper.convertEntityAndDto(cDto, Product.class);
		product.setCategory(categoryRepository.findById(categoryId).get());
		productRepo.save(product);
		return cDto;
	}

	@Override
	public List<ProductDto> findAll(String sortDirection, String sortBy, int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
