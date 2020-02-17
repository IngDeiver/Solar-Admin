package com.solar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.solar.model.Estacion;
import com.solar.model.Municipio;
import com.solar.model.Provedor;
import com.solar.service.EstacionServiceIMPL;
import com.solar.service.MunicipioServiceIMPL;
import com.solar.service.ProvedorServiceIMPL;
import com.solar.service.UserServiceImpl;


@Controller
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private EstacionServiceIMPL estacionServiceIMPL;
	
	@Autowired
	private MunicipioServiceIMPL municipioServiceIMPL;
	
	@Autowired
	private ProvedorServiceIMPL provedorServiceIMPL;
	
	@GetMapping("/login")
	public String login() {
		return "admin/login";
	}
	
	@PostMapping("/import")
	public String importFile(@RequestParam(name = "file") MultipartFile file, @RequestParam(name = "estacion") String estacion,
			RedirectAttributes ra) {
		
		try {
			Estacion existEstacion = estacionServiceIMPL.findByNombre_estacion(estacion.toUpperCase());
			
			if(existEstacion != null) {
				System.out.println(existEstacion.getNombre());
				if (userServiceImpl.saveRadiacion(file, existEstacion)) {
					ra.addFlashAttribute("ok", "Datos importados correctamente");
					
				}else {
					ra.addFlashAttribute("error", "Ocurrio un error al guardar 1");
				}
				
			}
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ra.addFlashAttribute("error", e.getMessage());
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		System.out.println(this.getEstaciones().size());
		model.addAttribute("estaciones", this.getEstaciones());
		model.addAttribute("provedores", this.getProvedores());
		model.addAttribute("municipios", this.getMunicipios());
		model.addAttribute("provedor", new Provedor());
		model.addAttribute("municipio", new Municipio());
		model.addAttribute("estacion", new Estacion());
		return "admin/import";
	}
	
	@PostMapping("/provedor")
	public String provedor(@ModelAttribute Provedor provedor, RedirectAttributes ra) {
		try {
			provedorServiceIMPL.save(provedor.getNombre());
			ra.addFlashAttribute("ok", "Provedor registrado");
		} catch (Exception e) {
			ra.addFlashAttribute("error", "No se pudo registrar el provedor");
		}
		return "redirect:/";
	}
	
	@PostMapping("/municipio")
	public String muncipio(@ModelAttribute Municipio municipio, RedirectAttributes ra) {
		try {
			municipioServiceIMPL.save(municipio.getNombre());
			ra.addFlashAttribute("ok", "Municipio registrado");
		} catch (Exception e) {
			ra.addFlashAttribute("error", "No se pudo registrar el municipio");
		}
		return "redirect:/";
	}
	
	@PostMapping("/estacion")
	public String estacion(@RequestParam(name = "estacion") String estacion, @RequestParam(name = "municipio") String municipio, 
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
		return "redirect:/";
	}
	
	private List<Estacion> getEstaciones() {
		return estacionServiceIMPL.list();
	}
	
	private List<Provedor> getProvedores() {
		return provedorServiceIMPL.list();
	}
	
	private List<Municipio> getMunicipios() {
		return municipioServiceIMPL.list();
	}


	
}
