package com.Java6.dto;

import java.util.List;

import com.Java6.entity.Authority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private String id;
	private String name;
	List<Authority> authorities;
}
