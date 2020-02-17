package com.solar.interfaces;

import java.util.List;

import com.solar.model.Municipio;;

public interface MunicipioService {
	public Municipio save(String municipio);
	public List<Municipio> list();
	public Municipio findByNombre(String nombre);
}
