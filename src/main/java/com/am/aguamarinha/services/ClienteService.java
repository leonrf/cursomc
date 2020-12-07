 package com.am.aguamarinha.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.am.aguamarinha.domain.Cliente;
import com.am.aguamarinha.repositories.ClienteRepository;

@Service 
public class ClienteService {

	@Autowired 
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) { // Função que busca o id
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElse(null);
	}
}
