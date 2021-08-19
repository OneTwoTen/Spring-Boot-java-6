package com.Java6.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.Java6.JPA.AccountRepository;
import com.Java6.Util.ObjectMapperUtils;
import com.Java6.Util.PageableUtils;
import com.Java6.dto.AccountDto;
import com.Java6.entity.Account;
import com.Java6.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository accountRepo;
	@Autowired
	private ObjectMapperUtils objectMapper;

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

	@Override
	public List<AccountDto> findAll(String sortDirection, String sortBy, int pageIndex, int pageSize) {
		Pageable pageable = PageableUtils.pageableUtils(sortDirection, sortBy, pageIndex, pageSize);
		List<Account> list = accountRepo.findAll(pageable).getContent();
		List<AccountDto> listdDto = objectMapper.mapAll(list, AccountDto.class);
		return listdDto;
	}

	@Override
	public boolean delete(String id) {
		Account account = accountRepo.getById(id);
		if (account != null) {
			accountRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AccountDto create(AccountDto cDto) {
		Account account = objectMapper.convertEntityAndDto(cDto, Account.class);
		accountRepo.save(account);
		cDto.setUsername(account.getUsername());
		return cDto;
	}

	@Override
	public AccountDto update(AccountDto cDto) {
		Account account = objectMapper.convertEntityAndDto(cDto, Account.class);
		account.setUsername(accountRepo.findById(account.getUsername()).get().getUsername());
		accountRepo.save(account);
		return cDto;
	}

	@Override
	public List<Account> getAdministrators() {
		// TODO Auto-generated method stub
		return accountRepo.getAdministrators();
	}

}
