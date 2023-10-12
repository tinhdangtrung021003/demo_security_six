package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.UserService;
import com.example.demo.entity.UserInfo;

/**
 * Created by HachNV on 31/05/2023
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	 UserService userService;

	    @PostMapping("/new")
	    public String addUser(@RequestBody UserInfo userInfo) {
	        return userService.addUser(userInfo);
	    }
}
