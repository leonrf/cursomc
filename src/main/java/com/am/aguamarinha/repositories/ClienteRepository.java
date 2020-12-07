package com.am.aguamarinha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.am.aguamarinha.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> { 
	
}
