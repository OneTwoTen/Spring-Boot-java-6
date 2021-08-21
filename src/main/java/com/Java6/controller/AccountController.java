package com.Java6.controller;

import java.security.Principal;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.Java6.JPA.AccountRepository;
import com.Java6.entity.Account;
import com.Java6.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    HttpServletRequest request;

    @GetMapping(value = "/register")
    public String register() {
        return "security/register";
    }

    @PostMapping(value = "/register")
    public String register(Model model, @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password, @RequestParam(value = "email") String email,
            @RequestParam(value = "fullname") String fullname) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setEmail(email);
        account.setFullname(fullname);
        account.setPhoto("");
        try {
            accountRepository.save(account);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println(account.toString());
        return "redirect:/security/login/form";
    }

    @GetMapping(value = "/user")
    public String user(Model model) {
        Principal principal = request.getUserPrincipal();
        Account account = accountRepository.findById(principal.getName()).get();
        model.addAttribute("account", account);
        return "security/user";
    }

    @PostMapping(value = "/user")
    public String user(Model model, Account account) {
        Principal principal = request.getUserPrincipal();
        account.setUsername(principal.getName());
        System.out.println(account.toString());
        account.setPhoto("");
        try {
            accountRepository.save(account);
            System.out.println("Success");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/user";
    }
}
