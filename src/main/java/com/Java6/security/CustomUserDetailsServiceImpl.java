package com.Java6.security;


import com.Java6.entity.Account;
import com.Java6.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findById(username);
        MyUserDetails myUserDetails = null;
        if (account == null) {
            System.out.println("null");
            throw new UsernameNotFoundException("Could not find account");
        } else {
            myUserDetails = new MyUserDetails();
            myUserDetails.setAccount(account);
        }
        return myUserDetails;
    }

}
