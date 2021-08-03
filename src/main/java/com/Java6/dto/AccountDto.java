package com.Java6.dto;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountDto {
    String username;
	String password;
	String fullname;
	String email;
	String photo;
	List<OrderDto> orders;
	List<AuthorityDto> authoritiesDto;
}
