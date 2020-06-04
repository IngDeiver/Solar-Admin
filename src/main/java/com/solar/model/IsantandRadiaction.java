package com.solar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IsantandRadiaction {

	private String obsTimeLocal;
	private String solarRadiationHigh;
	
	public IsantandRadiaction(){
		
	}

	public String getObsTimeLocal() {
		return obsTimeLocal;
	}

	public void setObsTimeLocal(String obsTimeLocal) {
		this.obsTimeLocal = obsTimeLocal;
	}

	public String getSolarRadiationHigh() {
		return solarRadiationHigh;
	}

	public void setSolarRadiationHigh(String solarRadiationHigh) {
		this.solarRadiationHigh = solarRadiationHigh;
	}

	
	
}
