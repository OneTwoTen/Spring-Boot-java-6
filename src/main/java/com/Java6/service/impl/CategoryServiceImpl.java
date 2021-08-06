package com.Java6.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.Java6.JPA.CategoryRepository;
import com.Java6.Util.ObjectMapperUtils;
import com.Java6.Util.PageableUtils;
import com.Java6.dto.CategoryDto;
import com.Java6.entity.Category;
import com.Java6.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	private ObjectMapperUtils objectMapper;

	@Override
	public List<CategoryDto> findAll(String sortDirection, String sortBy, int pageIndex, int pageSize) {
		Pageable pageable = PageableUtils.pageableUtils(sortDirection, sortBy, pageIndex, pageSize);
		List<Category> list = categoryRepository.findAll(pageable).getContent();
		List<CategoryDto> listdDto = objectMapper.mapAll(list, CategoryDto.class);
		return listdDto;
	}

	@Override
	public List<Category> findAll() {
		return (List<Category>) categoryRepository.findAll();
	}

	@Override
	public boolean delete(String id) {
		Category category = categoryRepository.getById(id);
		if (category != null) {
			categoryRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public CategoryDto create(CategoryDto categoryDto) {
		Category category = objectMapper.convertEntityAndDto(categoryDto, Category.class);
		categoryRepository.save(category);
		categoryDto.setId(category.getId());
		return categoryDto;
	}

	@Override
	public CategoryDto update(CategoryDto categoryDto) {
		Category category = objectMapper.convertEntityAndDto(categoryDto, Category.class);
		categoryRepository.save(category);
		return categoryDto;
	}

}
