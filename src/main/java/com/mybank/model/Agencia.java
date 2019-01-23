package com.mybank.model;

import java.util.ArrayList;
import java.util.List;

public enum Agencia {
	
	Agência0014(0014), Agência1218(1218), Agência1518(1518);
	
	private int descricao;

	private Agencia(int descricao) {
		this.descricao = descricao;
	}

	public static List<Agencia> agencia() {
		List<Agencia> agencias = new ArrayList<>();
		agencias.add(Agência0014);
		agencias.add(Agencia.Agência1218);
		agencias.add(Agência1518);
		return agencias;
	}

	public int getDescricao() {
		return descricao;
	}

	public void setDescricao(int descricao) {
		this.descricao = descricao;
	}

}
