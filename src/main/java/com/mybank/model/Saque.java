package com.mybank.model;

import java.io.Serializable;

public class Saque implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private double valor;
	private String senha;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
