package com.herby.pdv_tcc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.herby.pdv_tcc.domain.Cliente;

public class ClienteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preencheimento obrigatório")
	@Length(min=5, max=10, message="O tamanho dever ser entre 5 e 10 caracteres")
	private String codigo;
	
	@NotEmpty(message="Preencheimento obrigatório")
	@Length(min=5, max=120, message="O tamanho dever ser entre 5 e 120 caracteres")
	private String razaoSocial;
	
	@NotEmpty(message="Preencheimento obrigatório")
	@Email(message="Email inválido")
	private String email;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		codigo = obj.getCodigo();
		email = obj.getEmail();		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
