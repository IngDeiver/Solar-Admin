package com.solar.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.solar.model.Estacion;
import com.solar.model.Municipio;
import com.solar.model.Provedor;
import com.solar.model.Radiacion;
import com.solar.repository.RadiacionRepository;
import com.solar.service.EstacionServiceIMPL;
import com.solar.service.ProvedorServiceIMPL;
import com.solar.service.UserServiceImpl;


@Controller
public class UserController {

	@Autowired
	private RadiacionRepository radiacionRepository;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private EstacionServiceIMPL estacionServiceIMPL;
	
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
		model.addAttribute("provedor", new Provedor());
		model.addAttribute("municipio", new Municipio());
		model.addAttribute("estacion", new Estacion());
		return "admin/import";
	}
	
	@GetMapping("/download")
	public void  downloadData(HttpServletResponse response) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		String filename = "radiación solar - SPEC.csv";
		response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");
        
        ColumnPositionMappingStrategy<Radiacion> mappingStrategy=  new ColumnPositionMappingStrategy<Radiacion>(); 
        mappingStrategy.setType(Radiacion.class); 
        
        String[] columns = new String[]  
                { "fecha", "valor_radiacion"}; 
        mappingStrategy.setColumnMapping(columns);
        
      
     // Createing StatefulBeanToCsv object 
        StatefulBeanToCsvBuilder<Radiacion> builder = new StatefulBeanToCsvBuilder<Radiacion>(response.getWriter()); 
        
       StatefulBeanToCsv<Radiacion> beanWriter = builder.withMappingStrategy(mappingStrategy)
    		   .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
               .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
               .withOrderedResults(false)
               .build();

        // Write list to StatefulBeanToCsv object 
        beanWriter.write(radiacionRepository.findAll());

        // closing the writer object 
        response.getWriter().close();
	}
	
	
	
	
	
	
	private List<Estacion> getEstaciones() {
		return estacionServiceIMPL.list();
	}
	
	private List<Provedor> getProvedores() {
		return provedorServiceIMPL.list();
	}
	


	
}
