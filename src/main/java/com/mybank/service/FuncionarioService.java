package com.mybank.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.mybank.dao.FuncionarioDao;
import com.mybank.model.Funcionario;

@Service
public class FuncionarioService implements IFuncionarioService {

	private FuncionarioDao funcionarioDao;

	public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
		this.funcionarioDao = funcionarioDao;
	}

	@Override
	@Transactional
	public void SalvarOuAlterar(Funcionario cliente) {
		funcionarioDao.SalvarOuAlterar(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Funcionario buscarPorid(Integer id) {
		return funcionarioDao.buscarPorId(id);
	}

	@Override
	@Transactional
	public void excluir(Integer id) {
		funcionarioDao.excluir(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Funcionario> listar() {
		return funcionarioDao.listar();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Funcionario> search(Search search) {
		return funcionarioDao.searchDao(search);
	}

	public Funcionario alterar(Integer id) {
		return funcionarioDao.alterar(id);
	}

}
