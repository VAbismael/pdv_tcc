package com.herby.pdv_tcc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.herby.pdv_tcc.domain.Campanha;

public class CampanhaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Preenchimento Obrigatório")
	@Length(min = 5, max = 80, message = "O tamanho dever ser entre 5 e 80 caracteres")
	private String descricao;
	
	public CampanhaDTO() {
		
	}
	
	public CampanhaDTO(Campanha obj) {
		id = obj.getId();
		descricao = obj.getDescricao();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
