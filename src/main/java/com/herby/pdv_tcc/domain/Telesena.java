package com.herby.pdv_tcc.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Telesena extends Produto{
	private static final long serialVersionUID = 1L;
	

	private String numero;
	private Integer qtd;
	
	//Associação * para 1
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="campanha_id")
	private Campanha campanha;
	
	
	public Telesena() {
		
	}

	public Telesena(Integer id, String numero, double preco, Integer qtd,Campanha campanha) {
		super(id, preco);
		this.numero = numero;	
		this.qtd = qtd;
		this.campanha = campanha;			
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}
	
	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	
}
