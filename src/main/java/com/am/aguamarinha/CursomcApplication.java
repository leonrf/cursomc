package com.am.aguamarinha;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.am.aguamarinha.domain.Categoria;
import com.am.aguamarinha.domain.Cidade;
import com.am.aguamarinha.domain.Estado;
import com.am.aguamarinha.domain.Produto;
import com.am.aguamarinha.repositories.CategoriaRepository;
import com.am.aguamarinha.repositories.CidadeRepository;
import com.am.aguamarinha.repositories.EstadoRepository;
import com.am.aguamarinha.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository; // Objeto que permite salvar e coletar inf do bando de dados
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception { // Adicionando categoria no banco de dados
		// TODO Auto-generated method stub
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto("Computador", 2000.00);
		Produto p2 = new Produto("Impressora", 800.00);
		Produto p3 = new Produto("Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3)); // Crianda tabela de relação
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1)); 	// Crianda tabela de relação
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3)); // Salvando na tabela produtos
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2)); // Salvanda na tabela categoria
		
		Estado e1 = new Estado("Minas Gerais");
		Estado e2 = new Estado("São Paulo");

		Cidade c1 = new Cidade("Uberlândia", e1); // Adicionando os estados que as cidades pertecem
		Cidade c2 = new Cidade("São Paulo", e2);
		Cidade c3 = new Cidade("Campinas", e2);
		
		e1.getCidades().addAll(Arrays.asList(c2, c3)); // Adicionando as cidades associados com os estados
		e2.getCidades().addAll(Arrays.asList(c1));
		
		estadoRepository.saveAll(Arrays.asList(e1, e2)); // Primeiro salva estados!
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
	
	}

}
