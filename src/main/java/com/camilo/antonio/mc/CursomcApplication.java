package com.camilo.antonio.mc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.camilo.antonio.mc.domain.Categoria;
import com.camilo.antonio.mc.domain.Cidade;
import com.camilo.antonio.mc.domain.Cliente;
import com.camilo.antonio.mc.domain.Endereco;
import com.camilo.antonio.mc.domain.Estado;
import com.camilo.antonio.mc.domain.ItemPedido;
import com.camilo.antonio.mc.domain.Pagamento;
import com.camilo.antonio.mc.domain.PagamentoComBoleto;
import com.camilo.antonio.mc.domain.PagamentoComCartao;
import com.camilo.antonio.mc.domain.Pedido;
import com.camilo.antonio.mc.domain.Produto;
import com.camilo.antonio.mc.domain.enums.EstadoPagamento;
import com.camilo.antonio.mc.domain.enums.TipoCliente;
import com.camilo.antonio.mc.repositories.CategoriaRepository;
import com.camilo.antonio.mc.repositories.CidadeRepository;
import com.camilo.antonio.mc.repositories.ClienteRepository;
import com.camilo.antonio.mc.repositories.EnderecoRepository;
import com.camilo.antonio.mc.repositories.EstadoRepository;
import com.camilo.antonio.mc.repositories.ItemPedidoRepository;
import com.camilo.antonio.mc.repositories.PagamentoRepository;
import com.camilo.antonio.mc.repositories.PedidoRepository;
import com.camilo.antonio.mc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
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
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
					
				
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));	
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "Guarulhos", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		
		Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@email.com", "1231324689", TipoCliente.PESSOAFISICA);
		cliente1.getTelefones().addAll(Arrays.asList("998745631", "987542163"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardins","32156941", cliente1, c1);
		Endereco end2 = new Endereco(null, "Av Mattos", "105", "Sala 800", "Centro","98567123", cliente1, c2);
			
		
		cliente1.getEnderecos().addAll(Arrays.asList(end1,end2));
		
		clienteRepository.saveAll(Arrays.asList(cliente1));
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("02/10/2022 20:45"), cliente1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("02/10/2022 20:49"), cliente1, end2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("07/09/2022 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cliente1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido itemPedido1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido itemPedido2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido itemPedido3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(itemPedido1,itemPedido2));
		ped2.getItens().addAll(Arrays.asList(itemPedido3));

		p1.getItens().addAll(Arrays.asList(itemPedido1));
		p2.getItens().addAll(Arrays.asList(itemPedido3));
		p3.getItens().addAll(Arrays.asList(itemPedido2));

		itemPedidoRepository.saveAll(Arrays.asList(itemPedido1,itemPedido2,itemPedido3));
	}

}
