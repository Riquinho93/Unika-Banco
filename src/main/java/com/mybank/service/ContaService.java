package com.mybank.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.mybank.dao.ContaDao;
import com.mybank.model.Banco;
import com.mybank.model.Conta;
import com.mybank.model.Usuario;

@Service
public class ContaService implements IContaService {

	private ContaDao contaDao;

	public void setContaDao(ContaDao contaDao) {
		this.contaDao = contaDao;
	}

	@Override
	@Transactional
	public void SalvarOuAlterar(Conta conta) {
		contaDao.SalvarOuAlterar(conta);
	}

	@Override
	@Transactional(readOnly = true)
	public Conta buscarPorid(Integer id) {
		return contaDao.buscarPorId(id);
	}

	@Override
	@Transactional
	public void excluir(Integer id) {
		contaDao.excluir(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Conta> listar() {
		return contaDao.listar();
	}

	@Transactional(readOnly = true)
	public List<Usuario> listarUsuarios() {
		return contaDao.listarUsuarios();
	}

	@Transactional(readOnly = true)
	public List<Banco> listarBancos() {
		return contaDao.listarBancos();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Conta> search(Search search) {
		return contaDao.searchDao(search);
	}

	@Transactional(readOnly = true)
	public Conta alterar(Integer id) {
		return contaDao.alterar(id);
	}

}
