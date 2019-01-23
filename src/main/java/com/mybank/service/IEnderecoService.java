package com.mybank.service;

import java.util.List;

import com.googlecode.genericdao.search.Search;
import com.mybank.model.Endereco;

public interface IEnderecoService {

	public void SalvarOuAlterar(Endereco endereco);

	public Endereco buscarPorId(Integer id);

	public void excluir(Integer id);

	public List<Endereco> listar();
	
	public List<Endereco> search(Search search);
}
