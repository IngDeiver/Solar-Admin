package com.solar.interfaces;

import java.util.List;

import com.solar.model.Radiacion;

public interface WundergroundService {
	public List<Radiacion> getLastRadiation();
}
