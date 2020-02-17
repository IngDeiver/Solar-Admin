package com.solar.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.solar.model.Estacion;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, Integer> {
	public Estacion findByNombre(String nombre);
	
	String insertPointQuery = "UPDATE estaciones SET ubicacion = ST_SetSRID(ST_MakePoint(:lat, :lon),4326) WHERE id_estacion = :id";

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = insertPointQuery)
	int savePoint(@Param("lat") Double lat, @Param("lon") Double lon, @Param("id") Integer id);
}
