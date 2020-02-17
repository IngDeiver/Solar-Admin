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

@Entity(name = "provedores")
public class Provedor implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_provedor;
	
	public Provedor() {
		// TODO Auto-generated constructor stub
	}


	public Provedor(String nombre_provedor) {
		super();
		this.nombre = nombre_provedor;
	}






	public static long getSerialversionuid() {
		return serialVersionUID;
	}






	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "provedor")
	private List<Estacion> estaciones;
	
	
	public List<Estacion> getEstaciones() {
		return estaciones;
	}


	public void setEstaciones(List<Estacion> estaciones) {
		this.estaciones = estaciones;
	}


	
	
	
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre_provedor) {
		this.nombre = nombre_provedor;
	}






	@Column(unique = true, nullable = false, length = 100)
	private String nombre;

	

	


	public Long getId_provedor() {
		return id_provedor;
	}



	public void setId_provedor(Long id_provedor) {
		this.id_provedor = id_provedor;
	}



	
	
	
}
