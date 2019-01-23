package com.mybank.service;

import java.util.List;

import com.googlecode.genericdao.search.Search;
import com.mybank.model.Banco;


public interface IBancoService {
	

	public void SalvarOuAlterar(Banco banco);

	public Banco buscarPorId(Integer id);

	public void excluir(Integer idBanco);
	
	public List<Banco> listar();
	
	public List<Banco> search(Search search);
}
