package com.am.aguamarinha.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.am.aguamarinha.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> { 
	
}
