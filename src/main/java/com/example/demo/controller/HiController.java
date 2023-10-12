package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HiController {
	
	@GetMapping("/user/index")
	public String index(Model model) {
		return "/user/layout/home";
	}

	@GetMapping("/security/user")
	public String loginForm(Model model) {
		return "/user/security/login";
		
	}
	
	@GetMapping("/halo")
	public String halo(Model model) {
		return "/user/security/halo";
		
	}
}
