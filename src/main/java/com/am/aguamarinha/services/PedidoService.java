 package com.am.aguamarinha.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.am.aguamarinha.domain.Pedido;
import com.am.aguamarinha.repositories.PedidoRepository;

@Service 
public class PedidoService {

	@Autowired 
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) { // Função tem que chamar o objeto da repository
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElse(null);
	}
}
