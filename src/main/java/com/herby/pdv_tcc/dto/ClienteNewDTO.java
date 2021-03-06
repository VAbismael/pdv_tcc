package com.herby.pdv_tcc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.herby.pdv_tcc.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	// dados do cliente
	private Integer tipo;
	
	@NotEmpty(message="Preencheimento obrigatório")
	private String codigo, cnpjOuCpf, razaoSocial, email;
	
	// dados do endereco
	@NotEmpty(message="Preencheimento obrigatório")
	private String lugradouro, numero, complemento, bairro, cep;
	
	private String telefone1, telefone2;
	
	private Integer cidadeId;
	
	
	public ClienteNewDTO() {
		
	}


	public Integer getTipo() {
		return tipo;
	}


	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

		
	@Length(min=5, max=10, message="O tamanho dever ser entre 5 e 10 caracteres")
	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	public String getCnpjOuCpf() {
		return cnpjOuCpf;
	}

	public void setCnpjOuCpf(String cnpjOuCpf) {
		this.cnpjOuCpf = cnpjOuCpf;
	}


	public String getRazaoSocial() {
		return razaoSocial;
	}


	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	
	@Email(message="Email inválido")
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getLugradouro() {
		return lugradouro;
	}


	public void setLugradouro(String lugradouro) {
		this.lugradouro = lugradouro;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getComplemento() {
		return complemento;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public String getCep() {
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	public String getTelefone1() {
		return telefone1;
	}


	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}


	public String getTelefone2() {
		return telefone2;
	}


	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}


	public Integer getCidadeId() {
		return cidadeId;
	}


	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
	
	
}
