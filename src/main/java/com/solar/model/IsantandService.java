package com.solar.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IsantandService {

	private List<IsantandRadiaction> observations;

	public IsantandService() {
		
	}

	public List<IsantandRadiaction> getObservations() {
		return observations;
	}

	public void setObservations(List<IsantandRadiaction> observations) {
		this.observations = observations;
	}
	
	
	
	
}
