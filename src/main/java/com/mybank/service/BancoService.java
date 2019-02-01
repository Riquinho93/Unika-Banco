package com.mybank.service;

import java.util.ArrayList;
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

	@SuppressWarnings("null")
	@Override
	@Transactional
	public List<String> SalvarOuAlterar(Banco banco) {
		List<String> listaMessagens = new ArrayList<>();
		listaMessagens = validandoCampos(banco);
		if (listaMessagens == null || listaMessagens.isEmpty()) {
			bancoDao.SalvarOuAlterar(banco);
		} else {
			return listaMessagens;
		}
		return listaMessagens;
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

	@SuppressWarnings("null")
	public List<String> validandoCampos(Banco banco) {

		List<String> listaMessagens = new ArrayList<>();
		listaMessagens = null;

		if (banco.getNome() == null) {
			listaMessagens.add("Campo Nome é obrigatorio!");
		}
		if (banco.getEndereco().getBairro() == null) {
			listaMessagens.add("Campo Bairro é obrigatorio!");
		}
		if (banco.getEndereco().getLogradouro() == null) {
			listaMessagens.add("Campo Logradouro é obrigatorio!");
		}
		if (banco.getEndereco().getNumero() == null) {
			listaMessagens.add("Campo Numero é obrigatorio!");
		}
		if (banco.getEndereco().getCidade() == null) {
			listaMessagens.add("Campo Cidade é obrigatorio!");
		}

		return listaMessagens;
	}
}
