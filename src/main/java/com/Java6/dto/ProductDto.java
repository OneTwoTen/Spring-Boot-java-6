package com.Java6.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    Integer id;
	String name;
	String image;
	Double price;
	Date createDate = new Date();
	Boolean available;
	// CategoryDto categoryDto;
	String categoryId;

	// List<OrderDetailDto> orderDetailsDto;
}
