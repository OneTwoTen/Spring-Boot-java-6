package com.Java6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping(value="/")
    public String home(){
        return "redirect:/product/list";
    }
    @GetMapping(value="/admin/home/index")
    public String admin(){
        return "redirect:/assets/admin/index.html";
        // src\main\resources\static\assets\admin\index.html
    }
}
