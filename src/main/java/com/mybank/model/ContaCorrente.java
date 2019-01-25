package com.mybank.model;

public class ContaCorrente extends Conta {

	private static final long serialVersionUID = 1L;

	private double limite;

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

}
