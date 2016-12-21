package com.newmoney.boot.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
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
	
	@GetMapping(value = "/company/{ticker}/insider")
	public String getCompanyInsider(@PathVariable("ticker") String stockId, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		Company company = restTemplate.getForObject("http://localhost:8080/company/" + stockId, Company.class);
		Insider [] insiderData = restTemplate.getForObject("http://localhost:8080/company/" + stockId + "/insiders", Insider[].class);
		
		
		for(Insider id : insiderData) {
			Iterator<InsiderForm4> it = id.getForm4().iterator();
			
			while(it.hasNext()) {
				InsiderForm4 insiderForm4 = it.next();
				Company companyTest = restTemplate.getForObject("http://localhost:8080/insider/company/" + insiderForm4.getHeaderForm4Id(), Company.class);

				if(companyTest.getCik().equals(company.getCik())) {
					Derivative [] derivative = restTemplate.getForObject("http://localhost:8080/insiders/deriviative/" + insiderForm4.getHeaderForm4Id(), Derivative[].class);
					Nonderivative [] nonderivative = restTemplate.getForObject("http://localhost:8080/insiders/nonderiviative/" + insiderForm4.getHeaderForm4Id(), Nonderivative[].class);
					insiderForm4.setDerivative(Arrays.asList(derivative));
					insiderForm4.setNonderivative(Arrays.asList(nonderivative));
				}
				else {
					it.remove();
				}
			}
		}
		
		model.addAttribute("insiders", Arrays.asList(insiderData));
		model.addAttribute("company", company);
		return "company/insider";
	}
	
	@GetMapping(value="company/{ticker}/historical_data")
	public void getHistoricalDataFile(@PathVariable("ticker") String stockId, HttpServletResponse response) {
		try {
			RestTemplate restTemplate = new RestTemplate();
	        HistoricalDatum [] historicalData = restTemplate.getForObject("http://localhost:8080/historicaldata/" + stockId, HistoricalDatum[].class);
	        
			// get your file as InputStream
			String fileData = "date,open,high,low,close,volume,adjusted_close\n";
			for(HistoricalDatum hd : historicalData) {
				fileData += hd.getYear() + "-" + hd.getMonth() + "-" + hd.getDay();
				fileData += "," + hd.getOpen();
				fileData += "," + hd.getHigh();
				fileData += "," + hd.getLow();
				fileData += "," + hd.getClose();
				fileData += "," + hd.getVolume();
				fileData += "," + hd.getAdjustedClose() + "\n";
			}
			InputStream is = new ByteArrayInputStream(fileData.getBytes());
			
			// set MIME type of the file
	        String mimeType = "text/csv";

	        // set content attributes for the response
	        response.setContentType(mimeType);
	        response.setContentLength((int) fileData.length());
	 
	        // set headers for the response
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"", stockId + "_historical_data.csv");
	        response.setHeader(headerKey, headerValue);
	        
			// copy it to response's OutputStream
			IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();
	 
		} catch (IOException e) {
			System.out.println("Error writing file to output stream. " + e.getMessage());
		}
	}
}
