package com.am.aguamarinha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.am.aguamarinha.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> { 
	
}
