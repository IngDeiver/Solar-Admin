package com.solar.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.solar.model.RadiacionInfo;

@RestController
@RequestMapping("/api")
public class RadiacionController {

	@Autowired
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getRadiacion")
	public List<RadiacionInfo> getRadiacion() {
		Query query = em.createNativeQuery("select * from radiacion", RadiacionInfo.class);
		return (List<RadiacionInfo>) query.getResultList();
		
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getRadiacionByOrigen/{origen}")
	public List<RadiacionInfo> getRadiacionByOrigen(@PathVariable(name = "origen") String origen) {
		Query query = em.createNativeQuery("select * from GET_RADIACION_FROM_ORIGEN(:origen);", RadiacionInfo.class);
		query.setParameter("origen", origen);
		return (List<RadiacionInfo>) query.getResultList();
		
	}
	
}
