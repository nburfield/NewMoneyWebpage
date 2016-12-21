package com.newmoney.boot.RestResources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InsiderForm4 {

	InsiderForm4() {}
	
	public InsiderForm4(String headerForm4Id,
			Company company, Boolean isDirector, Boolean isOfficer,
			Boolean isTenPercent, Boolean isOther, String officerTitle, String date) {
		super();
		this.headerForm4Id = headerForm4Id;
		this.company = company;
		this.isDirector = isDirector;
		this.isOfficer = isOfficer;
		this.isTenPercent = isTenPercent;
		this.isOther = isOther;
		this.officerTitle = officerTitle;
		this.date = date;
	}

	private String headerForm4Id;
	private Company company;
	private Boolean isDirector;
	private Boolean isOfficer;
	private Boolean isTenPercent;
	private Boolean isOther;
	private String officerTitle;
	private String date;
	
	private List<Nonderivative> nonderivative;
	private List<Derivative> derivative;
	
	public List<Nonderivative> getNonderivative() {
		return nonderivative;
	}

	public void setNonderivative(List<Nonderivative> nonderivative) {
		this.nonderivative = nonderivative;
	}

	public List<Derivative> getDerivative() {
		return derivative;
	}

	public void setDerivative(List<Derivative> derivative) {
		this.derivative = derivative;
	}

	public String getHeaderForm4Id() {
		return headerForm4Id;
	}

	public void setHeaderForm4Id(String headerForm4Id) {
		this.headerForm4Id = headerForm4Id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Boolean getIsDirector() {
		return isDirector;
	}

	public void setIsDirector(Boolean isDirector) {
		this.isDirector = isDirector;
	}

	public Boolean getIsOfficer() {
		return isOfficer;
	}

	public void setIsOfficer(Boolean isOfficer) {
		this.isOfficer = isOfficer;
	}

	public Boolean getIsTenPercent() {
		return isTenPercent;
	}

	public void setIsTenPercent(Boolean isTenPercent) {
		this.isTenPercent = isTenPercent;
	}

	public Boolean getIsOther() {
		return isOther;
	}

	public void setIsOther(Boolean isOther) {
		this.isOther = isOther;
	}

	public String getOfficerTitle() {
		return officerTitle;
	}

	public void setOfficerTitle(String officerTitle) {
		this.officerTitle = officerTitle;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
