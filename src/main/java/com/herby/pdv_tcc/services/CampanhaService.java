package com.herby.pdv_tcc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.herby.pdv_tcc.domain.Campanha;
import com.herby.pdv_tcc.dto.CampanhaDTO;
import com.herby.pdv_tcc.repositories.CampanhaRepository;
import com.herby.pdv_tcc.services.exceptions.DataIntegrityException;
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
	
	public Campanha insert(Campanha obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Campanha update(Campanha obj) {
		Campanha newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(obj);
		
	}
	
	public void delete(Integer id) {		
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}	
	}
	
	public List<Campanha> findAll(){
		return repo.findAll();
	}
	
	public Page<Campanha> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction) , orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Campanha fromDTO(CampanhaDTO objDto) {
		return new Campanha(objDto.getId(), objDto.getDescricao());
	}
	
	private void updateData(Campanha newObj, Campanha obj) {
		newObj.setDescricao(obj.getDescricao());
	}
}
