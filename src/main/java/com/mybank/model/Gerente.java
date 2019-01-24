package com.mybank.model;

import javax.persistence.Entity;


public class Gerente extends Usuario {

	private static final long serialVersionUID = 1L;

	private String setor;

	// Getters e Setters

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

}
