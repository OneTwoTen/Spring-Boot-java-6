package com.Java6.service;

import java.util.List;

public interface BaseService<C, CDto> {
    List<C> findAll();
	List<CDto> findAll(String sortDirection, String sortBy, int pageIndex, int pageSize);
	boolean delete(String id);
	boolean delete(Integer id);
	CDto create(CDto cDto);
	CDto update(CDto cDto);
}
