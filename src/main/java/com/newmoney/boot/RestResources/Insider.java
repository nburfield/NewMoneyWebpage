package com.newmoney.boot.RestResources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Insider {
	
	Insider() {}
	
	private String cik;
	private String name;
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String zip;
	private String stateDescription;
	private List<InsiderForm4> form4;
	
	public String getCik() {
		return cik;
	}
	public void setCik(String cik) {
		this.cik = cik;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet1() {
		return street1;
	}
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	public String getStreet2() {
		return street2;
	}
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getStateDescription() {
		return stateDescription;
	}
	public void setStateDescription(String stateDescription) {
		this.stateDescription = stateDescription;
	}
	public List<InsiderForm4> getForm4() {
		return form4;
	}
	public void setForm4(List<InsiderForm4> form4) {
		this.form4 = form4;
	}
}
