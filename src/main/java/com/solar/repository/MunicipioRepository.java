package com.solar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solar.model.Municipio;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Long>{

		public Municipio findByNombre(String nombre);
}
