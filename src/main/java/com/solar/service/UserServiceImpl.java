package com.solar.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.solar.model.Estacion;
import com.solar.model.Radiacion;
import com.solar.repository.RadiacionRepository;

@Service
public class UserServiceImpl {

	@Autowired
	private RadiacionRepository radiacionRepository;
	
	
	
	
	public Boolean saveRadiacion(MultipartFile file, Estacion estacion, String format) throws IOException {

		Boolean ok = true;
		try {
			BufferedReader csv = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
			List<Radiacion> radiaciones = new ArrayList<Radiacion>();
			String line ;
			while((line = csv.readLine())!= null) {
				String[] fields = line.split(";");
				Radiacion currentRadiacion = new Radiacion();
				if(fields.length == 2) {
					
					try {
						currentRadiacion.setEstacion(estacion);
						DateFormat dateFormat = new SimpleDateFormat(format);
						String dateString = fields[0];
						if(dateString.contains("-")) {
							dateString = dateString.replace("-","/");
							System.out.println(dateString);
						}
						Date date = dateFormat.parse(dateString);
						long time = date.getTime();
						currentRadiacion.setFecha(new Timestamp(time));
						currentRadiacion.setValor_radiacion(Double.parseDouble(fields[1]));
						radiaciones.add(currentRadiacion);
					} catch (Exception e) {
						continue;
					}
				}
			}
			
			radiacionRepository.saveAll(radiaciones);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			ok = false;
		}
		return ok;
	}
}
