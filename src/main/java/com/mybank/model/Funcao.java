package com.mybank.model;

import java.util.ArrayList;
import java.util.List;

public enum Funcao {

	FACCIONISTA("Faccionista"), CORTADOR("Cortador"), FUNCIONARIO("Funcionario");
	private String descricaoFuncao;

	private Funcao(String descricaoFuncao) {
		this.descricaoFuncao = descricaoFuncao;
	}

	public static List<Funcao> funcoes() {
		List<Funcao> funcao = new ArrayList<>();
		funcao.add(CORTADOR);
		funcao.add(FACCIONISTA);
		funcao.add(FUNCIONARIO);
		return funcao;
	}

	public String getDescricaoFuncao() {
		return descricaoFuncao;
	}

	public void setDescricaoFuncao(String descricaoFuncao) {
		this.descricaoFuncao = descricaoFuncao;
	}
	
	public String getDescricao() {
		return descricaoFuncao;
	}

	public void setDescricao(String descricao) {
		this.descricaoFuncao = descricao;
	}
	
}
