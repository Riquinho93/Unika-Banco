package com.mybank.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.mybank.dao.GerenteDao;
import com.mybank.model.Gerente;

public class GerenteService implements IGerenteService {

	private GerenteDao gerenteDao;
	
	public GerenteDao getGerenteDao() {
		return gerenteDao;
	}

	public void setGerenteDao(GerenteDao gerenteDao) {
		this.gerenteDao = gerenteDao;
	}

	@Override
	@Transactional
	public void SalvarOuAlterar(Gerente gerente) {
		gerenteDao.SalvarOuAlterar(gerente);
	}

	@Override
	@Transactional(readOnly = true)
	public Gerente buscarPorId(Integer id) {
		return gerenteDao.buscarPorId(id);
	}

	@Override
	@Transactional
	public void excluir(Integer id) {
		gerenteDao.excluir(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Gerente> listar() {
		return gerenteDao.listar();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Gerente> search(Search search) {
		return gerenteDao.searchDao(search);
	}

}
