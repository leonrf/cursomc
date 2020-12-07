package com.am.aguamarinha;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.am.aguamarinha.domain.Categoria;
import com.am.aguamarinha.domain.Cidade;
import com.am.aguamarinha.domain.Cliente;
import com.am.aguamarinha.domain.Endereco;
import com.am.aguamarinha.domain.Estado;
import com.am.aguamarinha.domain.Produto;
import com.am.aguamarinha.domain.enums.TipoCliente;
import com.am.aguamarinha.repositories.CategoriaRepository;
import com.am.aguamarinha.repositories.CidadeRepository;
import com.am.aguamarinha.repositories.ClienteRepository;
import com.am.aguamarinha.repositories.EnderecoRepository;
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
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
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
		
		Cliente cli1 = new Cliente("Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393")); // Associando os telefones ao cliente
		
		Endereco end1 = new Endereco("Rua Flores", 300, "Apt 303", "Jardim", "38220834", cli1, c1);
		Endereco end2 = new Endereco("Avenida Matos", 105, "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));  // Associando os enderecos ao cliente
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
	}

}
