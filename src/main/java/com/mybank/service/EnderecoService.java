package com.mybank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mybank.dao.EnderecoDao;
import com.mybank.model.Endereco;


@Service
public class EnderecoService implements IEnderecoService{
	
	private EnderecoDao enderecoDao;

	public void setEnderecoDao(EnderecoDao enderecoDao) {
		this.enderecoDao = enderecoDao;
	}

	@Override
	public void SalvarOuAlterar(Endereco endereco) {
		enderecoDao.SalvarOuAlterar(endereco);		
	}

	@Override
	public Endereco buscarPorId(Integer id) {
		return enderecoDao.buscarPorId(id);
	}

	@Override
	public void excluir(Integer id) {
		enderecoDao.excluir(id);
	}

	@Override
	public List<Endereco> listar() {
		return enderecoDao.listar();
	}
	
}
