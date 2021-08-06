package com.Java6.service;

import com.Java6.dto.AccountDto;
import com.Java6.entity.Account;

public interface AccountService extends BaseService<Account, AccountDto>{

	Account findById(String username);
	boolean checkLogin(Account account);
}
