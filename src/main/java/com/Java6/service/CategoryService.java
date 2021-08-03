package com.Java6.service;

import java.util.List;

import com.Java6.dto.CategoryDto;
import com.Java6.entity.Category;

import org.springframework.data.domain.Sort.Direction;

public interface CategoryService{

	List<Category> findAll();
	List<CategoryDto> findAll(String sortDirection, String sortBy, int pageIndex, int pageSize);
	
}
