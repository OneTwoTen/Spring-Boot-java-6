package com.Java6.rest.controller;

import java.util.List;

import com.Java6.JPA.RoleRepository;
import com.Java6.entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/roles")
public class RoleRestController {
    
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}
