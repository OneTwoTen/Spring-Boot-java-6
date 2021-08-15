package com.Java6.dto;


import com.Java6.entity.Account;
import com.Java6.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorityDto {
    private Integer id;
	// private AccountDto accountDto;
	// private RoleDto roleDto;
	Account account;
	Role role;
}
