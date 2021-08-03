package com.Java6.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.Java6.JPA.AccountRepository;
import com.Java6.entity.Account;
import com.Java6.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository accountRepo;

	public static List<Account> listAccount = new ArrayList<Account>();

	@Override
	public Account findById(String username) {
		return accountRepo.findById(username).get();
	}

	public List<Account> findAll() {
		return listAccount;
	}

	public boolean checkLogin(Account account) {
		for (Account accountList : listAccount) {
		  if (StringUtils.pathEquals(account.getUsername(), accountList.getUsername())
			  && StringUtils.pathEquals(account.getPassword(), accountList.getPassword())) {
			return true;
		  }
		}
		return false;
	  }

}
