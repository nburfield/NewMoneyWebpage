package com.newmoney.boot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.newmoney.boot.model.User;
import com.newmoney.boot.model.UserValidation;
import com.newmoney.boot.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping(value = {"/", "/login"})
	public String login(Model model) {
		model.addAttribute("userValidation", new UserValidation());
		return "user/login";
	}
			
	@PostMapping("/login")
	public ModelAndView login(@ModelAttribute UserValidation user) {
		System.out.println("Login Post: ");
		System.out.println(user.getEmail() + " " + user.getFirstName() + " " + user.getLastName() + " " + user.getPassword());
		
		User loginUser = userRepository.findByEmail(user.getEmail());
		if(loginUser == null) {
			return new ModelAndView("redirect:/login");
		}
		
		System.out.println(loginUser.getEncryptedPassword());
		
		if(user.getAuthenticate(loginUser.getEncryptedPassword(), loginUser.getSalt())) {
			return new ModelAndView("redirect:/about");
		}
		else {
			return new ModelAndView("redirect:/login");
		}		
	}
	
	@GetMapping(value = "/register")
	public String register(Model model) {
		model.addAttribute("userValidation", new UserValidation());
		return "user/register";
	}
	
	@PostMapping("/register")
	public ModelAndView register(@ModelAttribute UserValidation user) {
		System.out.println("Register Post: ");
		System.out.println(user.getEmail() + " " + user.getFirstName() + " " + user.getLastName() + " " + user.getPassword());
		
		User loginUser = userRepository.findByEmail(user.getEmail());
		if(loginUser != null) {
			return new ModelAndView("redirect:/register");
		}
		
		user.setAuthenticate();
		User saveUser = new User(user.getEmail(), user.getFirstName(), user.getLastName(), user.getEncryptedPassword(), user.getSalt(), 1);
		userRepository.save(saveUser);
		
		return new ModelAndView("redirect:/about");
	}
	
	@GetMapping(value = "/about")
	public String about() {
		return "general/about";
	}
}
