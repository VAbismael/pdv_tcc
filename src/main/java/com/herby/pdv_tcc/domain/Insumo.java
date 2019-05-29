package com.herby.pdv_tcc.domain;

import javax.persistence.Entity;

@Entity
public class Insumo extends Produto{
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	
	public Insumo() {
		
	}
	
	
	public Insumo(Integer id, double preco, String descricao) {
		super(id, preco);
		this.descricao = descricao;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	

}
