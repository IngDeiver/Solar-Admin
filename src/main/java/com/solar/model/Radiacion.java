package com.solar.model;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "radiaciones")
public class Radiacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_radiacion;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="id_estacion")
	private Estacion estacion;
	
	private Double valor_radiacion;
	
	private Timestamp fecha; 
	
	public Radiacion() {
		// TODO Auto-generated constructor stub
	}

	

	public Radiacion(Estacion estacion, Double valor_radiacion, Timestamp fecha) {
		super();
		this.estacion = estacion;
		this.valor_radiacion = valor_radiacion;
		this.fecha = fecha;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}



	public Long getId_radiacion() {
		return id_radiacion;
	}

	public void setId_radiacion(Long id_radiacion) {
		this.id_radiacion = id_radiacion;
	}

	public Estacion getEstacion() {
		return estacion;
	}

	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}

	public Double getValor_radiacion() {
		return valor_radiacion;
	}

	public void setValor_radiacion(Double valor_radiacion) {
		this.valor_radiacion = valor_radiacion;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	
	
	
	
}
