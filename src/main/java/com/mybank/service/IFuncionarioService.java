package com.mybank.service;

import java.util.List;

import com.googlecode.genericdao.search.Search;
import com.mybank.model.Funcionario;

public interface IFuncionarioService {

	public void SalvarOuAlterar(Funcionario cliente);

	public Funcionario buscarPorid(Integer id);

	public void excluir(Integer id);

	public List<Funcionario> listar();

	public List<Funcionario> search(Search search);
}
