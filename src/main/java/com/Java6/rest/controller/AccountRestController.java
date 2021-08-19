package com.Java6.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.Java6.entity.Account;
import com.Java6.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest/accounts")
public class AccountRestController {

   
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAccountById(@PathVariable String id) {

    return null;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(HttpServletRequest request, @RequestBody Account account) {
       
        return null;
    }

    @GetMapping()
    public List<Account> getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
        if(admin.orElse(false)) {
            return accountService.getAdministrators();
        }
        return accountService.findAll();
    }
}
