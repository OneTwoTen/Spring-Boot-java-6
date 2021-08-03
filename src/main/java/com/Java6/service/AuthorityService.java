package com.Java6.service;

import java.util.List;

import com.Java6.entity.Account;
import com.Java6.entity.Authority;

public interface AuthorityService {
    List<Authority> findByAccount(Account account);
}
