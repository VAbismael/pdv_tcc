package com.herby.pdv_tcc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.herby.pdv_tcc.domain.Campanha;
import com.herby.pdv_tcc.domain.Categoria;
import com.herby.pdv_tcc.domain.Cidade;
import com.herby.pdv_tcc.domain.Cliente;
import com.herby.pdv_tcc.domain.Endereco;
import com.herby.pdv_tcc.domain.Estado;
import com.herby.pdv_tcc.domain.Insumo;
import com.herby.pdv_tcc.domain.ItemPedido;
import com.herby.pdv_tcc.domain.Pagamento;
import com.herby.pdv_tcc.domain.PagamentoComBoleto;
import com.herby.pdv_tcc.domain.Pedido;
import com.herby.pdv_tcc.domain.Produto;
import com.herby.pdv_tcc.domain.Telesena;
import com.herby.pdv_tcc.domain.enums.EstadoPagamento;
import com.herby.pdv_tcc.domain.enums.TipoCliente;
import com.herby.pdv_tcc.repositories.CampanhaRepository;
import com.herby.pdv_tcc.repositories.CategoriaRepository;
import com.herby.pdv_tcc.repositories.CidadeRepository;
import com.herby.pdv_tcc.repositories.ClienteRepository;
import com.herby.pdv_tcc.repositories.EnderecoRepository;
import com.herby.pdv_tcc.repositories.EstadoRepository;
import com.herby.pdv_tcc.repositories.ItemPedidoRepository;
import com.herby.pdv_tcc.repositories.PagamentoRepository;
import com.herby.pdv_tcc.repositories.PedidoRepository;
import com.herby.pdv_tcc.repositories.ProdutoRepository;

@SpringBootApplication
public class PdvTccApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CampanhaRepository campanhaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPrdidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PdvTccApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Categoria cat1 = new Categoria(null, "Telesena");
		Categoria cat2 = new Categoria(null, "Banner");
			
		Campanha camp1 = new Campanha(null, "Ano Novo");
		Campanha camp2 = new Campanha(null, "Carnaval");
			
		Produto tel1 = new Telesena(null, "0001", 250, 100, camp1);
		Produto ins1 = new Insumo(null, 15, "Urna Telesena");
		
		cat1.getProdutos().addAll(Arrays.asList(tel1));		
		ins1.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));		
		campanhaRepository.saveAll(Arrays.asList(camp1, camp2));				
		produtoRepository.saveAll(Arrays.asList(tel1, ins1));
		
		Estado est1 = new Estado(null, "Pernambuco");
		Estado est2 = new Estado(null, "Paraíba");
		
		Cidade c1 = new Cidade(null, "Recife", est1);
		Cidade c2 = new Cidade(null, "Paulista", est1);		
		Cidade c3 = new Cidade(null, "João Pessoa", est2);
		Cidade c4 = new Cidade(null, "Cabedelo", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1, c2));
		est2.getCidades().addAll(Arrays.asList(c3, c4));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
		
		Cliente cli1 = new Cliente(null, TipoCliente.PESSOAFISICA, "0001M", "000.000.000-25", "herby@harby.com");
		
		cli1.getTelefones().addAll(Arrays.asList("986061647", "74616089"));
		
		Endereco end1 = new Endereco(null, "Rua 115, nº 56", "casa", "Jardim Paulista", "53470-110", cli1, c2);
		Endereco end2 = new Endereco(null, "Rua União Soviética, 620", "Apt.: 102", "Pau Amarelo", "53433-070", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));		
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2019 10:22"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("30/10/2019 12:22"), cli1, end2);
		
		Pagamento pag1 = new PagamentoComBoleto(null, EstadoPagamento.QUITADO, ped1, sdf.parse("05/10/2019 14:52"), sdf.parse("10/10/2019 00:00"));
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PEDENTE, ped2, null, sdf.parse("10/11/2019 00:00"));
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		
		ItemPedido ip1 = new ItemPedido(ped1, ins1, 2, 500);
		ItemPedido ip2 = new ItemPedido(ped1, tel1, 1, 15);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		
		tel1.getItens().addAll(Arrays.asList(ip1));
		ins1.getItens().addAll(Arrays.asList(ip1));
		
		itemPrdidoRepository.saveAll(Arrays.asList(ip1, ip2));
		
	}
	
}
