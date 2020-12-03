 package com.am.aguamarinha.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.am.aguamarinha.domain.Categoria;
import com.am.aguamarinha.repositories.CategoriaRepository;

@Service 
public class CategoriaService {

	@Autowired 
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) { // Função tem que chamar o objeto da repository
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}
}
