package com.herby.pdv_tcc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.herby.pdv_tcc.domain.Campanha;
import com.herby.pdv_tcc.services.CampanhaService;

@RestController
@RequestMapping(value="/campanhas")
public class CampanhaResource {
	
	@Autowired
	private CampanhaService service;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Campanha obj = service.find(id);
		return ResponseEntity.ok().body(obj);

	}

}
