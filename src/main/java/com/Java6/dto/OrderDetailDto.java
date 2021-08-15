package com.Java6.dto;

import com.Java6.entity.Order;
import com.Java6.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {
    Long id;
	Double price;
	Integer quantity;
	// ProductDto productDto;
	// OrderDto orderDto;
	Product product;
	Order order;
}
