package com.herby.pdv_tcc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.herby.pdv_tcc.domain.Campanha;
import com.herby.pdv_tcc.domain.Cidade;
import com.herby.pdv_tcc.domain.Cliente;
import com.herby.pdv_tcc.domain.Endereco;
import com.herby.pdv_tcc.domain.Estado;
import com.herby.pdv_tcc.domain.Telesena;
import com.herby.pdv_tcc.domain.enums.TipoCliente;
import com.herby.pdv_tcc.repositories.CampanhaRepository;
import com.herby.pdv_tcc.repositories.CidadeRepository;
import com.herby.pdv_tcc.repositories.ClienteRepository;
import com.herby.pdv_tcc.repositories.EnderecoRepository;
import com.herby.pdv_tcc.repositories.EstadoRepository;
import com.herby.pdv_tcc.repositories.TelesenaRepository;

@SpringBootApplication
public class PdvTccApplication implements CommandLineRunner{
	
	@Autowired
	private CampanhaRepository campanhaRepository;
	
	@Autowired
	private TelesenaRepository telesenaRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PdvTccApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Campanha camp1 = new Campanha(null, "Ano Novo");
		Campanha camp2 = new Campanha(null, "Carnaval");
		
		campanhaRepository.saveAll(Arrays.asList(camp1, camp2));
		
		Telesena tel1 = new Telesena(null, "0001", 250, 100, camp1);
		
		telesenaRepository.saveAll(Arrays.asList(tel1));
		
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
		

		
		
		
	}
	
}
