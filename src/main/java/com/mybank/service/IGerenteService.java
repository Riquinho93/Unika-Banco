package com.mybank.service;

import java.util.List;

import com.googlecode.genericdao.search.Search;
import com.mybank.model.Gerente;

public interface IGerenteService {
	
	public void SalvarOuAlterar(Gerente gerente);

	public Gerente buscarPorId(Integer id);

	public void excluir(Integer id);

	public List<Gerente> listar();
	
	public List<Gerente> search(Search search);

}
