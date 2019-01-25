package com.mybank.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.mybank.dao.UsuarioDao;
import com.mybank.model.Funcionario;
import com.mybank.model.Usuario;

@Service
public class UsuarioService implements IUsuarioService {

	private UsuarioDao usuarioDao;

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@Override
	@Transactional
	public void SalvarOuAlterar(Usuario pessoa) {
		usuarioDao.SalvarOuAlterar(pessoa);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario buscarPorId(Integer id) {
		return usuarioDao.buscarPorId(id);
	}

	@Override
	@Transactional
	public void excluir(Integer id) {
		usuarioDao.excluir(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> listar() {
		return usuarioDao.listar();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> search(Search search) {
		return usuarioDao.searchDao(search);
	}

	public Usuario alterar(Integer id) {
		return usuarioDao.alterar(id);
	}

}
