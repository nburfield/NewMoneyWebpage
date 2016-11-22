package com.newmoney.boot.RestResources;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricalData {

	private List<HistoricalDatum> hd;
	
	public HistoricalData() {}

	public List<HistoricalDatum> getHd() {
		return hd;
	}

	public void setHd(List<HistoricalDatum> hd) {
		this.hd = hd;
	}
}
