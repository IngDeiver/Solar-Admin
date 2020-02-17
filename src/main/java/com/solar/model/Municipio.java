package com.solar.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "municipios")
public class Municipio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_municipio;
	
	@OneToMany(mappedBy = "municipio", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Estacion> estaciones;
	
	public Municipio() {
		// TODO Auto-generated constructor stub
	}
	

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public Long getId_municipio() {
		return id_municipio;
	}



	public void setId_municipio(Long id_municipio) {
		this.id_municipio = id_municipio;
	}



	public List<Estacion> getEstaciones() {
		return estaciones;
	}



	public void setEstaciones(List<Estacion> estaciones) {
		this.estaciones = estaciones;
	}



	public Municipio(String nombre_municipio) {
		super();
		this.nombre = nombre_municipio;
	}


	@Column(unique = true, nullable = false, length = 100)
	private String nombre;



	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre_municipio) {
		this.nombre = nombre_municipio;
	}
	
	
}
