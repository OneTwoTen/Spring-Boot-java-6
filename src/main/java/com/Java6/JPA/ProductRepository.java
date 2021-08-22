package com.Java6.JPA;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Java6.dto.ProductDto;
import com.Java6.entity.Product;
public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
	List<Product> findByCategoryId(String cid);

	@Query("SELECT COUNT(*) FROM Product")
	Integer getTotalProductCount();

	@Query("SELECT p FROM Product p WHERE p.name like ?1")
	List<Product> findAll(String name, Pageable pageable);
}
