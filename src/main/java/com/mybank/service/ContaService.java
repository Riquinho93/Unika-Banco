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

	@Override
	@Transactional(readOnly = true)
	public List<Conta> search(Search search) {
		return contaDao.searchDao(search);
	}

	@Transactional(readOnly = true)
	public Conta alterar(Integer id) {
		return contaDao.alterar(id);
	}

	public List<Conta> depositar(Integer numeroConta, double valor) {

		Search search = new Search(Conta.class);
		search.addFilterEqual("numeroConta", numeroConta);

		List<Conta> lista = search(search);

		if (lista != null && !lista.isEmpty()) {
			Conta conta = lista.get(0);
			double total = conta.getSaldo() + valor;
			conta.setSaldo(total);
			SalvarOuAlterar(conta);
		} else {
			System.out.println("Conta não Existe!!!");
		}
		return lista;

	}

	public void saque(Conta conta, double valor, String senha) {

		// IMPORTANTE
		// Tem uma taxa na mesma quantia do Banco 24 horas de R$ 2

		if (senha.equals(conta.getSenha())) {
			if (conta.getSaldo() >= valor) {
				conta.setSaldo(conta.getSaldo() - valor - 2);
				SalvarOuAlterar(conta);
			} else {
				System.out.println("Saldo Infuficente!!!");
			}
		} else {
			System.out.println("Errro Saque!!!");
		}

	}

	public void transferir(Conta contaUsuario, Conta contaDestinario, double valor) {
		// IMPORTANTE
		// Tem uma taxa na mesma quantia do Banco Santader de R$17,40 por evento.

		if (contaUsuario.getBanco().equals(contaDestinario.getBanco())) {
			if (contaUsuario.getSaldo() >= valor) {
				contaUsuario.setSaldo(contaUsuario.getSaldo() - valor);
				contaDestinario.setSaldo(contaDestinario.getSaldo() + valor);
				SalvarOuAlterar(contaUsuario);
				SalvarOuAlterar(contaDestinario);
			} else {
				System.out.println("Saldo Infuciente");
			}
		} else {
			if (contaUsuario.getSaldo() >= valor) {
				contaUsuario.setSaldo(contaUsuario.getSaldo() - valor - 0.58);
				contaDestinario.setSaldo(contaDestinario.getSaldo() + valor);
				SalvarOuAlterar(contaUsuario);
				SalvarOuAlterar(contaDestinario);
			} else {
				System.out.println("Saldo Infuciente");
			}
		}

	}

}
