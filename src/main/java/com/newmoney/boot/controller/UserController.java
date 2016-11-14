package com.newmoney.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.newmoney.boot.model.User;
import com.newmoney.boot.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(value = {"/", "/login"})
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "user/login";
	}
}
