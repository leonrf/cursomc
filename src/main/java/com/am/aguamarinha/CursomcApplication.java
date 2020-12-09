package com.am.aguamarinha;

import java.text.SimpleDateFormat;
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
import com.am.aguamarinha.domain.ItemPedido;
import com.am.aguamarinha.domain.Pagamento;
import com.am.aguamarinha.domain.PagamentoComBoleto;
import com.am.aguamarinha.domain.PagamentoComCartao;
import com.am.aguamarinha.domain.Pedido;
import com.am.aguamarinha.domain.Produto;
import com.am.aguamarinha.domain.enums.EstadoPagamento;
import com.am.aguamarinha.domain.enums.TipoCliente;
import com.am.aguamarinha.repositories.CategoriaRepository;
import com.am.aguamarinha.repositories.CidadeRepository;
import com.am.aguamarinha.repositories.ClienteRepository;
import com.am.aguamarinha.repositories.EnderecoRepository;
import com.am.aguamarinha.repositories.EstadoRepository;
import com.am.aguamarinha.repositories.ItemPedidoRepository;
import com.am.aguamarinha.repositories.PagamentoRepository;
import com.am.aguamarinha.repositories.PedidoRepository;
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm"); // Mascara de formatação para instanciar uma data
		
		Pedido ped1 = new Pedido(sdf.parse("30/09/2017 10:32"), cli1, end1); // Instanciando pedido
		Pedido ped2 = new Pedido(sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pagto1 = new PagamentoComCartao(EstadoPagamento.QUITADO, ped1, 6); // Instanciando pagamento
		ped1.setPagamento(pagto1); // Informando que este é o pagamento do pedido 1
		
		Pagamento pagto2 = new PagamentoComBoleto(EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2)); // Associando os pedidos ao cliente
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2)); // Salvando os pedidos
		
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));  // Salvando os pagamentos
		
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p1.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}

}
