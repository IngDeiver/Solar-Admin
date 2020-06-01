package com.solar.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity(name = "estaciones")
public class Estacion implements Serializable{
	 
	private static final long serialVersionUID = 1L;


	public Estacion() {
		// TODO Auto-generated constructor stub
	}

	

	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_estacion;
	
	@Column(unique = true, nullable = false, length = 100, name = "nombre_estacion")
	private String nombre;
	
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_municipio")
	private Municipio municipio;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_origen")
	private Provedor provedor;
	
	@Column(nullable = false)
	private Double lat;
	
	@Column(nullable = false)
	private Double lon;
	
	@OneToMany(mappedBy = "estacion", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Radiacion> radiaciones;
	
	
	
	public Double getLat() {
		return lat;
	}





	public Estacion(String nombre, Municipio municipio, Provedor proveedor, Double lat, Double lon) {
		super();
		this.nombre = nombre;
		this.municipio = municipio;
		this.provedor = proveedor;
		this.lat = lat;
		this.lon = lon;
	}





	public void setLat(Double lat) {
		this.lat = lat;
	}





	public Double getLon() {
		return lon;
	}





	public void setLon(Double lon) {
		this.lon = lon;
	}





	public List<Radiacion> getRadiaciones() {
		return radiaciones;
	}
	public void setRadiaciones(List<Radiacion> radiaciones) {
		this.radiaciones = radiaciones;
	}
	public Integer getId_estacion() {
		return id_estacion;
	}
	public void setId_estacion(Integer id_estacion) {
		this.id_estacion = id_estacion;
	}
	public String getNombre_estacion() {
		return nombre;
	}
	public void setNombre_estacion(String nombre_estacion) {
		this.nombre = nombre_estacion;
	}
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	public Provedor getProveedor() {
		return provedor;
	}
	public void setProveedor(Provedor proveedor) {
		this.provedor = proveedor;
	}
	
	
}
