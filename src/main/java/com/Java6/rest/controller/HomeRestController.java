package com.Java6.rest.controller;

import java.util.stream.Collectors;

import com.Java6.Util.JwtUtilties;
import com.Java6.dto.JwtRequest;
import com.Java6.dto.JwtResponse;
import com.Java6.entity.Account;
import com.Java6.entity.Authority;
import com.Java6.security.CustomUserDetailsServiceImpl;
import com.Java6.security.MyUserDetails;
import com.Java6.service.AuthorityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {

    // @Autowired
    // private CustomUserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtilties JwtUtilties;
    @Autowired
	private UserDetailsService userDetailsService;

    @Autowired
	private AuthenticationManager authenticationManager;
    @Autowired
    private AuthorityService authorityService;
    // @GetMapping(value = "/")
    // public String home() {
    //     return "hello world";
    // }

    @PostMapping(value = "/authenticate")
    public JwtResponse authenticate(@RequestBody Account jwtRequest) throws Exception {
        // for(Authority authority: authorityService.findByAccount(jwtRequest)) {
        //     System.out.println(authority.getRole().getName());
        // }
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (BadCredentialsException e) {
            System.out.println("Lỗi");
            throw new Exception("Invalid credentials", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        final String token = JwtUtilties.generateToken(userDetails);
        return new JwtResponse(token);
    }
}
