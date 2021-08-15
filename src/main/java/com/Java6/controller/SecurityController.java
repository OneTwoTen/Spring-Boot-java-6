package com.Java6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin("*")
@Controller
public class SecurityController {
	
	@RequestMapping(value = "/security/login/form")
	public String loginForm(Model model) {
		model.addAttribute("message", "Vui lòng đăng nhập");
		return "security/login";
	}
	
	@RequestMapping(value = "/security/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message", "Đăng nhập thành công");
		System.out.println("success");
		return "security/login";
	}

	@RequestMapping(value = "/security/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Không đúng tài khoản hoặc mật khẩu");
		System.out.println("false login"); 
		return "security/login";
	}

	@RequestMapping(value = "/security/unauthorized")
	public String unauthorized(Model model) {
		model.addAttribute("message", "Không có quyền truy cập");
		return "security/login";
	}

	@RequestMapping(value="/security/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("message", "Bạn đã đăng xuất");
		return "security/login";
	}
}
