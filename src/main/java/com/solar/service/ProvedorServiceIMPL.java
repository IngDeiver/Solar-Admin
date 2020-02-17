package com.solar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solar.interfaces.ProvedorService;
import com.solar.model.Provedor;
import com.solar.repository.ProvedorRepository;

@Service
public class ProvedorServiceIMPL implements ProvedorService{

	@Autowired
	private ProvedorRepository provedorRepository;

	@Override
	public Provedor save(String provedor) {
		return provedorRepository.save(new Provedor(provedor));
	}

	@Override
	public List<Provedor> list() {
		return provedorRepository.findAll();
	}

	@Override
	public Provedor findByNombre(String nombre) {
		return provedorRepository.findByNombre(nombre);
	}
	
	
}
