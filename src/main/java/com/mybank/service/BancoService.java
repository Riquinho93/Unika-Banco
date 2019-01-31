package com.mybank.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.mybank.dao.BancoDao;
import com.mybank.model.Banco;

@Service
public class BancoService implements IBancoService {

	private BancoDao bancoDao;

	public void setBancoDao(BancoDao bancoDao) {
		this.bancoDao = bancoDao;
	}

	@Override
	@Transactional
	public boolean SalvarOuAlterar(Banco banco) {
		boolean fazendoValidacao = validandoCampos(banco);
		if (fazendoValidacao == false) {
			bancoDao.SalvarOuAlterar(banco);
		} else {
			return fazendoValidacao;
		}
		return fazendoValidacao;
	}

	@Override
	@Transactional(readOnly = true)
	public Banco buscarPorId(Integer id) {
		return bancoDao.buscarPorId(id);
	}

	@Override
	@Transactional
	public void excluir(Integer idBanco) {
		bancoDao.excluir(idBanco);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Banco> listar() {
		return bancoDao.listar();
	}

	public List<Banco> search(Search search) {
		return bancoDao.searchDao(search);

	}

	@Transactional(readOnly = true)
	public Banco alterar(Integer id) {
		return bancoDao.alterar(id);
	}

	public boolean validandoCampos(Banco banco) {

		boolean valida = false;

		if (banco.getNome() == null) {
			valida = true;
		}

		return valida;
	}
}
