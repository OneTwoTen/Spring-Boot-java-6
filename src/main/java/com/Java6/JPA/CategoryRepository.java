package com.Java6.JPA;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Java6.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String>{

}
