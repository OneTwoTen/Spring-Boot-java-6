package com.Java6.service;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;

public interface HelperService<C> {
    List<C> findAll();

    List<C> findAll(Direction sortDirection, String sortBy, int pageIndex, int pageSize);

    C findOne(String id);

    C findOne(Integer id);

    String add(C type);

    String remove(String id);

    String remove(Integer id);
}
