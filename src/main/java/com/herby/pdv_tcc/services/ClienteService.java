package com.herby.pdv_tcc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.herby.pdv_tcc.domain.Cidade;
import com.herby.pdv_tcc.domain.Cliente;
import com.herby.pdv_tcc.domain.Endereco;
import com.herby.pdv_tcc.domain.enums.TipoCliente;
import com.herby.pdv_tcc.dto.ClienteDTO;
import com.herby.pdv_tcc.dto.ClienteNewDTO;
import com.herby.pdv_tcc.repositories.ClienteRepository;
import com.herby.pdv_tcc.repositories.EnderecoRepository;
import com.herby.pdv_tcc.services.exceptions.DataIntegrityException;
import com.herby.pdv_tcc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
		
	}
	
	public void delete(Integer id) {		
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}	
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction) , orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), null, objDto.getCodigo(), null, objDto.getRazaoSocial(),objDto.getEmail());
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, TipoCliente.toEnum(objDto.getTipo()), objDto.getCodigo(), objDto.getCnpjOuCpf(), objDto.getRazaoSocial(), objDto.getEmail());
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLugradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		return cli;
	}
	
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setCodigo(obj.getCodigo());
		newObj.setRazaoSocial(obj.getRazaoSocial());
		newObj.setEmail(obj.getEmail());
	}
}
