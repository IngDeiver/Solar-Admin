package com.solar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.solar.model.Provedor;
import com.solar.service.ProvedorServiceIMPL;

@Controller
@RequestMapping("/provedores")
public class ProvedorController {

	@Autowired
	private ProvedorServiceIMPL provedorServiceIMPL;
	
	@PostMapping
	public String saveProvedor(@ModelAttribute Provedor provedor, RedirectAttributes ra) {
		try {
			provedorServiceIMPL.save(provedor.getNombre());
			ra.addFlashAttribute("ok", "Provedor registrado");
		} catch (Exception e) {
			ra.addFlashAttribute("error", "No se pudo registrar el provedor");
		}
		return "redirect:/provedores";
	}
	
	@GetMapping
	public String listProvedores() {
		return "";
	}
	
}
