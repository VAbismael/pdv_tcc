package com.herby.pdv_tcc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/campanhas")
public class CampanhaResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public String listar() {
		return "REST está funcinando!";
	}

}
