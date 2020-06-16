package com.solar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
public class EstacionInfo {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;
	
	private String nombre;
	private Double lat;
	private Double lng;
	private String municipio;
	private String origen;
	
	
	
	public EstacionInfo() {
		super();
	}



	public EstacionInfo(Integer id, String nombre, Double lat, Double lon, String municipio, String origen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.lat = lat;
		this.lng = lon;
		this.municipio = municipio;
		this.origen = origen;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Double getLat() {
		return lat;
	}



	public void setLat(Double lat) {
		this.lat = lat;
	}



	public Double getLon() {
		return lng;
	}



	public void setLon(Double lon) {
		this.lng = lon;
	}



	public String getMunicipio() {
		return municipio;
	}



	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}



	public String getOrigen() {
		return origen;
	}



	public void setOrigen(String origen) {
		this.origen = origen;
	}

	
	
	
	
}
