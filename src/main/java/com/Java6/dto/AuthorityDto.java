package com.Java6.dto;


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
	private AccountDto accountDto;
	private RoleDto roleDto;
}
