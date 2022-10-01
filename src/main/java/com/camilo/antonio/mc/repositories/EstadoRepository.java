package com.camilo.antonio.mc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camilo.antonio.mc.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Integer>{
	
	

}
