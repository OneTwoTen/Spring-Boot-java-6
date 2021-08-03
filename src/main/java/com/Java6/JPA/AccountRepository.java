package com.Java6.JPA;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Java6.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String>{
	
}
