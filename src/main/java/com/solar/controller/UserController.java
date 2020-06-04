package com.solar.controller;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
import com.solar.model.RadiacionInfo;
import com.solar.service.EstacionServiceIMPL;
import com.solar.service.UserServiceImpl;


@Controller
public class UserController {

	@Autowired
	private EntityManager em;
	

	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private EstacionServiceIMPL estacionServiceIMPL;
	
	
	@GetMapping("/login")
	public String login() {
		return "admin/login";
	}
	
	@PostMapping("/import")
	public String importFile(@RequestParam(name = "file") MultipartFile file, @RequestParam(name = "estacion") String estacion,
			RedirectAttributes ra) {
		
		try {
			Estacion existEstacion = estacionServiceIMPL.findByNombre_estacion(estacion);
			
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
		model.addAttribute("estaciones", this.getEstaciones());
		return "admin/import";
	}
	
	@GetMapping("/download")
	public void  downloadData(HttpServletResponse response, @RequestParam(name = "estacion") String estacion) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		System.out.println("Estacion -> " + estacion);
		String filename = "radiación solar - SPEC.csv";
		response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");
        
        ColumnPositionMappingStrategy<RadiacionInfo> mappingStrategy=  new ColumnPositionMappingStrategy<RadiacionInfo>(); 
        mappingStrategy.setType(RadiacionInfo.class); 
        
        String[] columns = new String[]  
                {"id","estacion","municipio","origen","lat","lon","radiacion","fecha"}; 
        mappingStrategy.setColumnMapping(columns);
        
      
     // Createing StatefulBeanToCsv object 
        StatefulBeanToCsvBuilder<RadiacionInfo> builder = new StatefulBeanToCsvBuilder<RadiacionInfo>(response.getWriter()); 
        
       StatefulBeanToCsv<RadiacionInfo> beanWriter = builder.withMappingStrategy(mappingStrategy)
    		   .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
               .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
               .withOrderedResults(false)
               .build();
        // Write list to StatefulBeanToCsv object 
       Query query = em.createNativeQuery("select * from GET_RADIACION_FROM_ESTACION(:estacion);", RadiacionInfo.class);
       if(estacion.equals("all_stations")) {
    	   query = em.createNativeQuery("select * from radiacion;", RadiacionInfo.class);
       }else {
    	   query.setParameter("estacion", estacion);
       }
       @SuppressWarnings("unchecked")
       List<RadiacionInfo> radiacion = (List<RadiacionInfo>) query.getResultList();
       beanWriter.write(radiacion);
        // closing the writer object 
        response.getWriter().close();
	}
	
	
	
	
	
	
	private List<Estacion> getEstaciones() {
		return estacionServiceIMPL.list();
	}
	

	


	
}
