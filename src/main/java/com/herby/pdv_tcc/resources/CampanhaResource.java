package com.herby.pdv_tcc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.herby.pdv_tcc.domain.Campanha;
import com.herby.pdv_tcc.dto.CampanhaDTO;
import com.herby.pdv_tcc.services.CampanhaService;

@RestController
@RequestMapping(value="/campanhas")
public class CampanhaResource {
	
	@Autowired
	private CampanhaService service;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public ResponseEntity<Campanha> find(@PathVariable Integer id) {
		Campanha obj = service.find(id);
		return ResponseEntity.ok().body(obj);

	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CampanhaDTO objDto){
		Campanha obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CampanhaDTO objDto, @PathVariable Integer id){
		Campanha obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CampanhaDTO>> findAll() {
		List<Campanha> list = service.findAll();
		List<CampanhaDTO> listDto = list.stream().map(obj -> new CampanhaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);

	}
	
	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CampanhaDTO>> findPage(
			@RequestParam(value="page",defaultValue = "0") Integer page, 
			@RequestParam(value="linesPerPage",defaultValue = "24")Integer linesPerPage, 
			@RequestParam(value="orderBy",defaultValue = "descricao")String orderBy, 
			@RequestParam(value="direction",defaultValue = "ASC")String direction) {
		Page<Campanha> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CampanhaDTO> listDto = list.map(obj -> new CampanhaDTO(obj));
		return ResponseEntity.ok().body(listDto);

	}
}
