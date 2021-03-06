package com.am.aguamarinha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.am.aguamarinha.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> { 
	
}
