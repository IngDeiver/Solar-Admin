package com.solar.model;

import java.sql.Timestamp;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

//@SqlResultSetMapping(name = "RadiacionInfo", classes = {
//		@ConstructorResult(targetClass = RadiacionInfo.class, columns = {
//				@ColumnResult(name = "Estacion"),@ColumnResult(name = "Municipio"),@ColumnResult(name = "Origen"),
//				@ColumnResult(name = "Lat"),@ColumnResult(name = "Long"),@ColumnResult(name = "Radiacion"),
//				@ColumnResult(name = "Fecha"), @ColumnResult(name = "Id")
//		})
//})

@Entity
@Immutable
@Table(name="radiacion")
public class RadiacionInfo {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	private String Estacion;
	private String Municipio;
	private String Origen;
	private Double Lat;
	private Double Long;
	private Double Radiacion;
	private Timestamp Fecha;
	
	
	public RadiacionInfo(String estacion, String municipio, String origen, Double lat, Double l, Double radiacion,
			Timestamp fecha) {
		super();
		Estacion = estacion;
		Municipio = municipio;
		Origen = origen;
		Lat = lat;
		Long = l;
		Radiacion = radiacion;
		Fecha = fecha;
	}

	public RadiacionInfo() {
		
	}
	
	
	
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getEstacion() {
		return Estacion;
	}
	public void setEstacion(String estacion) {
		Estacion = estacion;
	}
	public String getMunicipio() {
		return Municipio;
	}
	public void setMunicipio(String municipio) {
		Municipio = municipio;
	}

	public String getOrigen() {
		return Origen;
	}

	public void setOrigen(String origen) {
		Origen = origen;
	}

	public Double getLat() {
		return Lat;
	}
	public void setLat(Double lat) {
		Lat = lat;
	}
	public Double getLong() {
		return Long;
	}
	public void setLong(Double l) {
		Long = l;
	}
	public Double getRadiacion() {
		return Radiacion;
	}
	public void setRadiacion(Double radiacion) {
		Radiacion = radiacion;
	}
	public Timestamp getFecha() {
		return Fecha;
	}
	public void setFecha(Timestamp fecha) {
		Fecha = fecha;
	}
	
	
	
}
