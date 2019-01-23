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
	public void SalvarOuAlterar(Funcionario funcionario) {
		funcionarioDao.SalvarOuAlterar(funcionario);
	}

	@Override
	@Transactional(readOnly = true)
	public Funcionario buscarPorId(Integer id) {
		return funcionarioDao.buscarPorId(id);
	}

	@Override
	@Transactional
	public void excluir(Integer idFuncionario) {
		funcionarioDao.excluir(idFuncionario);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Funcionario> listar() {
		return funcionarioDao.listar();
	}

	public List<Funcionario> search(Search search) {
		return funcionarioDao.searchDao(search);

	}
	
	@Transactional(readOnly = true)
	public Funcionario alterar(Integer id) {
		return funcionarioDao.alterar(id);
	}

}
