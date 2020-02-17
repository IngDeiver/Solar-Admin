package com.solar.interfaces;

import java.util.List;

import com.solar.model.Estacion;
import com.solar.model.Municipio;
import com.solar.model.Provedor;

public interface EstacionService {
	public Estacion save(String estacion, Municipio mu, Provedor pro,Double la, Double lon);
	public List<Estacion> list();
	public Estacion findById(Integer id);
	public Estacion findByNombre_estacion(String nombre_estacion);
}
