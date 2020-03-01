package com.solar.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.solar.model.Municipio;
import com.solar.model.Provedor;
import com.solar.service.EstacionServiceIMPL;
import com.solar.service.MunicipioServiceIMPL;
import com.solar.service.ProvedorServiceIMPL;

@Controller
@RequestMapping("/estaciones")
public class EstacionController {
	
	@Autowired
	private EstacionServiceIMPL estacionServiceIMPL;
	
	@Autowired
	private MunicipioServiceIMPL municipioServiceIMPL;
	
	@Autowired
	private ProvedorServiceIMPL provedorServiceIMPL;
	
	@PostMapping
	public String saveEstacion(@RequestParam(name = "estacion") String estacion, @RequestParam(name = "municipio") String municipio, 
			@RequestParam(name = "provedor") String provedor, RedirectAttributes ra, @RequestParam(name = "lat") String lat,
			@RequestParam(name = "lon") String lon) {
		try {
			Municipio municipioExist = municipioServiceIMPL.findByNombre(municipio);
			Provedor provedorExist = provedorServiceIMPL.findByNombre(provedor);
			estacionServiceIMPL.save(estacion, municipioExist, provedorExist, Double.parseDouble(lat), Double.parseDouble(lon));
			ra.addFlashAttribute("ok", "Estacion registrada");
		} catch (Exception e) {
			ra.addFlashAttribute("error", "No se pudo registrar la estación");
		}
		return "redirect:/estaciones";
	}
	
	@GetMapping
	public String listEstaciones() {
		return "";
	}
	
}
