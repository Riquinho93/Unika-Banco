package com.mybank.model;

import java.io.Serializable;

public abstract class Conta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	protected double saldo;
	protected int conta;
	protected TipoConta tipoConta;
	
	public abstract void atualizarSaldo();

}
