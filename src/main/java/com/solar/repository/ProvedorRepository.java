package com.solar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.solar.model.Provedor;

@Repository
public interface ProvedorRepository extends JpaRepository<Provedor, Long> {
	public Provedor findByNombre(String nombre);
}
