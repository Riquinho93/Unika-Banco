package com.mybank.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.genericdao.search.Search;
import com.mybank.dao.ClienteDao;
import com.mybank.model.Cliente;

@Service
public class ClienteService implements IClienteService{
	
	private ClienteDao clienteDao;
	
	public void setUsuarioDao(ClienteDao clienteDao) {
		this.clienteDao = clienteDao;
	}

	@Override
	@Transactional
	public void SalvarOuAlterar(Cliente cliente) {
		clienteDao.SalvarOuAlterar(cliente);		
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente buscarPorid(Integer id) {
		return clienteDao.buscarPorId(id);
	}

	@Override
	@Transactional
	public void excluir(Integer id) {
		clienteDao.excluir(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> listar() {
		return clienteDao.listar();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> search(Search search) {
		return clienteDao.searchDao(search);
	}

	public Cliente alterar(Integer id) {
		return clienteDao.alterar(id);
	}

}
