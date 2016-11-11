package com.newmoney.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.newmoney.boot.model.User;

@Controller
public class UserController {
	
	@GetMapping(value = {"/", "/login"})
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "user/login";
	}
}
