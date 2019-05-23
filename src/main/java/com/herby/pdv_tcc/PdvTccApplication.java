package com.herby.pdv_tcc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.herby.pdv_tcc.domain.Campanha;
import com.herby.pdv_tcc.repositories.CampanhaRepository;

@SpringBootApplication
public class PdvTccApplication implements CommandLineRunner{
	
	@Autowired
	private CampanhaRepository campanhaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PdvTccApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Campanha camp1 = new Campanha(null, "Ano Novo");
		Campanha camp2 = new Campanha(null, "Carnaval");
		
		campanhaRepository.saveAll(Arrays.asList(camp1, camp2));
	}
	
}
