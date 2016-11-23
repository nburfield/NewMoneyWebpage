package com.newmoney.boot.controller;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.newmoney.boot.RestResources.Company;
import com.newmoney.boot.RestResources.HistoricalDatum;

@Controller
public class CompanyController {
	
	@GetMapping(value = "/company")
	public String getCompanies(Model model) {
		RestTemplate restTemplate = new RestTemplate();
		Company [] companies = restTemplate.getForObject("http://localhost:8080/company", Company[].class);
        		
        model.addAttribute("companies", Arrays.asList(companies));
		return "company/viewall";
	}
	
	@GetMapping(value = "/company/{ticker}")
	public String getCompany(@PathVariable("ticker") String stockId, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		Company company = restTemplate.getForObject("http://localhost:8080/company/" + stockId, Company.class);
        HistoricalDatum [] historicalData = restTemplate.getForObject("http://localhost:8080/historicaldata/" + stockId, HistoricalDatum[].class);
        		
        model.addAttribute("historicalData", Arrays.asList(historicalData));
		model.addAttribute("company", company);
		return "company/view";
	}
	
	@GetMapping(value = "/company/chart/{ticker}")
	public String getCompanyChart(@PathVariable("ticker") String stockId, Model model) {
		RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("http://localhost:8080/company/" + stockId, Company.class);
        HistoricalDatum [] historicalData = restTemplate.getForObject("http://localhost:8080/historicaldata/" + stockId, HistoricalDatum[].class);
        		
        model.addAttribute("historicalData", Arrays.asList(historicalData));
		model.addAttribute("company", company);
		return "company/chart";
	}
}
