package com.mybank.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mybank.model.Banco;
import com.mybank.model.Conta;
import com.mybank.model.Usuario;

@Repository
public class ContaDao extends GenericDao<Conta, Serializable> {

	@SuppressWarnings("unchecked")
	// @Transactional(readOnly = true)
	public List<Conta> listar() {
		String hql = "select f from Conta f";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		List<Conta> userList = query.list();
		return userList;
	}

	// @Transactional(readOnly = true)
	public Conta alterar(Integer id) {
		String hql = "select f from Conta f " + "where f.id = :id";

		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		Conta user = (Conta) query.uniqueResult();
		return user;
	}
	
	@SuppressWarnings("unchecked")
	// @Transactional(readOnly = true)
	public List<Usuario> listarUsuarios() {
		String hql = "select f from Usuario f ";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		List<Usuario> userList = query.list();
		return userList;
	}
	
	@SuppressWarnings("unchecked")
	// @Transactional(readOnly = true)
	public List<Banco> listarBancos() {
		String hql = "select f from Banco f";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		List<Banco> userList = query.list();
		return userList;
	}
}
