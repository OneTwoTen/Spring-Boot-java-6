package com.Java6.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.Java6.entity.Account;
import com.Java6.entity.Authority;
import com.Java6.service.AuthorityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUserDetails implements UserDetails {

	/**
	 * 
	 */
	private Account account;

	@Autowired
	private AuthorityService authorityService;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Authority> listAuthority = account.getAuthorities();
		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for(Authority authority: listAuthority) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + authority.getRole().getId()));
        }
		return authorities;
	}

	@Override
	public String getPassword() {
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		return account.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
