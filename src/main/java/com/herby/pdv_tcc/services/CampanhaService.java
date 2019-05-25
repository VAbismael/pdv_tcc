package com.herby.pdv_tcc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herby.pdv_tcc.domain.Campanha;
import com.herby.pdv_tcc.repositories.CampanhaRepository;
import com.herby.pdv_tcc.services.exceptions.ObjectNotFoundException;

@Service
public class CampanhaService {
	
	@Autowired
	private CampanhaRepository repo;
	
	public Campanha find(Integer id) {
		Optional<Campanha> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Campanha.class.getName()));
	}
	
}
