package com.newmoney.boot.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.newmoney.boot.RestResources.Company;
import com.newmoney.boot.RestResources.HistoricalData;
import com.newmoney.boot.RestResources.HistoricalDatum;
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
	
	
	@GetMapping(value = "/company/{ticker}")
	public String getCompany(@PathVariable("ticker") String stockId, Model model) {
		RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("http://localhost:8080/", Company.class);
        
        HistoricalDatum [] historicalData = restTemplate.getForObject("http://localhost:8080/" + stockId, HistoricalDatum[].class);
        		
        model.addAttribute("historicalData", Arrays.asList(historicalData));
		model.addAttribute("company", company);
		return "company/view";
	}
}
