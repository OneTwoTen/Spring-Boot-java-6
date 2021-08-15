package com.Java6.dto;

import java.util.Date;
import java.util.List;

import com.Java6.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    Long id;
	String address;
	Date createDate = new Date();
	// AccountDto accountDto;
	Account account;
	// List<OrderDetailDto> orderDetailDtoList; 
}
