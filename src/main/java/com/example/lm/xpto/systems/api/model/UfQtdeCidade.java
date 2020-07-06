package com.example.lm.xpto.systems.api.model;

public class UfQtdeCidade {

	private String uf;
	
	private Long qtdeCidades;

	public UfQtdeCidade() {
		//Construtor padrão
	}
	
	public UfQtdeCidade(final String uf, final Long qtdeCidades) {
		this.uf = uf;
		this.qtdeCidades = qtdeCidades;
	}
	
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Long getQtdeCidades() {
		return qtdeCidades;
	}

	public void setQtdeCidades(Long qtdeCidades) {
		this.qtdeCidades = qtdeCidades;
	}
}
