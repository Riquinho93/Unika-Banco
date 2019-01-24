package com.mybank.service;

import java.util.List;

import com.googlecode.genericdao.search.Search;
import com.mybank.model.Cliente;

public interface IClienteService {

	public void SalvarOuAlterar(Cliente cliente);

	public Cliente buscarPorid(Integer id);

	public void excluir(Integer id);

	public List<Cliente> listar();

	public List<Cliente> search(Search search);
}
