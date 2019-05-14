package com.herby.pdv_tcc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.herby.pdv_tcc.domain.Campanha;

@RestController
@RequestMapping(value="/campanhas")
public class CampanhaResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Campanha> listar() {
		
		Campanha camp1 = new Campanha(1, "Dia das mães");
		Campanha camp2 = new Campanha(2, "São João");
		
		List<Campanha> lista = new ArrayList<>();
		lista.add(camp1);
		lista.add(camp2);
		
		return lista;
	}

}
