package com.newmoney.boot.controller;

import java.util.Arrays;
import java.util.Iterator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.newmoney.boot.RestResources.Company;
import com.newmoney.boot.RestResources.Derivative;
import com.newmoney.boot.RestResources.HistoricalDatum;
import com.newmoney.boot.RestResources.Insider;
import com.newmoney.boot.RestResources.InsiderForm4;
import com.newmoney.boot.RestResources.Nonderivative;

@Controller
public class InsiderController {
	
	@GetMapping(value = "/insider")
	public String getCompanies(Model model) {
		RestTemplate restTemplate = new RestTemplate();
		Insider [] insiders = restTemplate.getForObject("http://localhost:8080/insider", Insider[].class);
        		
        model.addAttribute("insiders", Arrays.asList(insiders));
		return "insider/viewall";
	}
	
	@GetMapping(value = "/insider/{cik}")
	public String getCompany(@PathVariable("cik") String insiderId, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		Insider insider = restTemplate.getForObject("http://localhost:8080/insider/" + insiderId, Insider.class);
        		
		for(InsiderForm4 insiderForm4 : insider.getForm4()) {
			Company company = restTemplate.getForObject("http://localhost:8080/insider/company/" + insiderForm4.getHeaderForm4Id(), Company.class);
			Derivative [] derivative = restTemplate.getForObject("http://localhost:8080/insiders/deriviative/" + insiderForm4.getHeaderForm4Id(), Derivative[].class);
			Nonderivative [] nonderivative = restTemplate.getForObject("http://localhost:8080/insiders/nonderiviative/" + insiderForm4.getHeaderForm4Id(), Nonderivative[].class);
			insiderForm4.setDerivative(Arrays.asList(derivative));
			insiderForm4.setNonderivative(Arrays.asList(nonderivative));
			insiderForm4.setCompany(company);
		}
		
		model.addAttribute("insider", insider);
		return "insider/view";
	}
}
