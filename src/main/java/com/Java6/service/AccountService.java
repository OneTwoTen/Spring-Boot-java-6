package com.Java6.service;

import com.Java6.entity.Account;

public interface AccountService {

	Account findById(String username);
	boolean checkLogin(Account account);
}
