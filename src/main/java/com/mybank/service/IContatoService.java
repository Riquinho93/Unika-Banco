package com.mybank.service;

import java.util.List;

import com.googlecode.genericdao.search.Search;
import com.mybank.model.Contato;

public interface IContatoService {

	public void SalvarOuAlterar(Contato contato);

	public Contato buscarPorid(Integer id);

	public void excluir(Integer id);

	public List<Contato> listar();

	public List<Contato> search(Search search);

}
