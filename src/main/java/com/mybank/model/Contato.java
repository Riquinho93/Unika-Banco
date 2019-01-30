package com.mybank.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "contato")
public class Contato extends Usuario {

	private static final long serialVersionUID = 1L;

	private Integer numCpf;
	private Integer numeroConta;

	public Integer getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Integer numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Integer getNumCpf() {
		return numCpf;
	}

	public void setNumCpf(Integer numCpf) {
		this.numCpf = numCpf;
	}

}
