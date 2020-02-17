package com.solar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solar.interfaces.EstacionService;
import com.solar.model.Estacion;
import com.solar.model.Municipio;
import com.solar.model.Provedor;
import com.solar.repository.EstacionRepository;

@Service
public class EstacionServiceIMPL implements EstacionService {

	@Autowired
	private EstacionRepository estacionRepository;

	@Override
	public Estacion save(String estacion, Municipio mu, Provedor pro, Double lat, Double lon) {
		Estacion estacionSinUbicacion = estacionRepository.save(new Estacion(estacion, mu, pro, lat, lon));
		return estacionSinUbicacion;
	}

	@Override
	public List<Estacion> list() {
		return estacionRepository.findAll();
	}

	@Override
	public Estacion findById(Integer id) {
		Estacion estacion = estacionRepository.findById(id).get();
		System.out.println(estacion.getNombre_estacion());
		return estacion;
	}

	@Override
	public Estacion findByNombre_estacion(String nombre) {
		return estacionRepository.findByNombre(nombre);
	}


}
