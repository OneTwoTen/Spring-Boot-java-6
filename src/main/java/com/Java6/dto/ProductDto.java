package com.Java6.dto;

import java.util.Date;

import com.Java6.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto {
    Integer id;
	String name;
	String image;
	Double price;
	Date createDate = new Date();
	Boolean available;
	// CategoryDto categoryDto;
	// String categoryId;
	Category category;
	// List<OrderDetailDto> orderDetailsDto;
}
