package com.mybank.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.mybank.dao.ContatoDao;
import com.mybank.model.Contato;

@Service
public class ContatoService implements IContatoService {

	private ContatoDao contatoDao;

	public void setContatoDao(ContatoDao contatoDao) {
		this.contatoDao = contatoDao;
	}

	@Override
	@Transactional
	public void SalvarOuAlterar(Contato contato) {
		contatoDao.SalvarOuAlterar(contato);
	}

	@Override
	@Transactional(readOnly = true)
	public Contato buscarPorid(Integer id) {
		return contatoDao.buscarPorId(id);
	}

	@Override
	@Transactional
	public void excluir(Integer id) {
		contatoDao.excluir(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Contato> listar() {
		return contatoDao.listar();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Contato> search(Search search) {
		return contatoDao.searchDao(search);
	}

}
